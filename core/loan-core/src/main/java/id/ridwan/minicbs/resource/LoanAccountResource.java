package id.ridwan.minicbs.resource;

import id.ridwan.minicbs.domain.account.LoanAccountDto;
import id.ridwan.minicbs.service.LoanAccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/loan-accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanAccountResource {

    @Inject
    LoanAccountService service;

    @GET
    public List<LoanAccountDto> listAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public LoanAccountDto getById(@PathParam("id") UUID id) {
        return service.findById(id);
    }

    @POST
    public Response create(LoanAccountDto dto) {
        return Response.status(Response.Status.CREATED)
                .entity(service.create(dto))
                .build();
    }

    @PUT
    @Path("/{id}")
    public LoanAccountDto update(@PathParam("id") UUID id, LoanAccountDto dto) {
        return service.update(id, dto);
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
