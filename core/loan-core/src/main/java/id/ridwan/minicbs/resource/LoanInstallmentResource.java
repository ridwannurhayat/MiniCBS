package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.domain.installment.LoanInstallmentDto;
import id.ridwan.minicbs.service.LoanInstallmentService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/loan-installments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanInstallmentResource {

    @Inject
    LoanInstallmentService service;

    @GET
    public List<LoanInstallmentDto> listAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public LoanInstallmentDto getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @POST
    public Response create(LoanInstallmentDto dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public LoanInstallmentDto update(@PathParam("id") UUID id, LoanInstallmentDto dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
