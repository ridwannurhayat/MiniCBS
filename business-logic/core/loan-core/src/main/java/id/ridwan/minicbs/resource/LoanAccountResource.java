package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.service.LoanAccountService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDate;
import java.util.Map;
import java.util.UUID;

@Path("/loan-accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanAccountResource {

    @Inject
    LoanAccountService service;

    @GET
    public Uni<Map<String, Object>> listAll(
            @QueryParam("fromDueDate") LocalDate fromDueDate,
            @QueryParam("toDueDate") LocalDate toDueDate,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size
    ) {
        return service.findPagedAndFiltered(fromDueDate, toDueDate, page, size);
    }

    @GET
    @Path("/{id}")
    public Uni<LoanAccountDto> getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @POST
    public Uni<LoanAccountDto> create(LoanAccountDto dto) {
        return service.create(dto);
    }

    @PUT
    @Path("/{id}")
    public Uni<LoanAccountDto> update(@PathParam("id") UUID id, LoanAccountDto dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Void> delete(@PathParam("id") UUID id) {
        return service.delete(id);
    }
}
