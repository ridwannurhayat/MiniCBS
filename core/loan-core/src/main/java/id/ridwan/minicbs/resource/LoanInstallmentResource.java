package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import id.ridwan.minicbs.service.LoanInstallmentService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Path("/loan-accounts/{loanAccountId}/loan-installments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanInstallmentResource {

    @Inject
    LoanInstallmentService service;

    @GET
    public Uni<Map<String, Object>> listAll(
            @PathParam("loanAccountId") UUID loanAccountId,
            @QueryParam("fromDate") LocalDate fromDate,
            @QueryParam("toDate") LocalDate toDate,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size
    ) {
        return service.findPagedAndFiltered(loanAccountId, fromDate, toDate, page, size);
    }

    @GET
    @Path("/{id}")
    public Uni<LoanInstallmentDto> getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @POST
    public Uni<LoanInstallmentDto> create(LoanInstallmentDto dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public Uni<LoanInstallmentDto> update(@PathParam("id") UUID id, LoanInstallmentDto dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") UUID id) {
        return service.delete(id);
    }
}
