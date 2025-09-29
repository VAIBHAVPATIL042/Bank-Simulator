package com.bank.api;

import com.bank.dto.AccountRequest;
import com.bank.model.Account;
import com.bank.service.AccountService;
import com.bank.serviceImpl.AccountServiceImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AccountController {

    private AccountService service = new AccountServiceImpl();

    @POST
    @Path("/accounts")
    @Produces(MediaType.APPLICATION_JSON)   // ensures JSON response
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(AccountRequest request) {
        boolean created = service.createAccount(request);

        if (created) {
            return Response.status(Response.Status.CREATED)
                    .entity("{\"message\":\"Account created successfully\"}")
                    .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\":\"Failed to create account\"}")
                    .build();
        }
    }


//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response createAccount(AccountRequest request) {
//        boolean created = service.createAccount(request);
//        if (created) {
//            return Response.status(Response.Status.CREATED).entity("Account created successfully").build();
//        } else {
//            return Response.status(Response.Status.BAD_REQUEST).entity("Account creation failed").build();
//        }
//    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        return Response.ok(service.getAllAccounts()).build();
    }

    @GET
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByNumber(@PathParam("accountNumber") String accountNumber) {
        return Response.ok(service.getByAccountNumber(accountNumber)).build();
    }

    @PUT
    @Path("/{accountNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(@PathParam("accountNumber") String accountNumber, AccountRequest request) {
        boolean updated = service.updateAccount(accountNumber, request);
        if (updated) {
            return Response.ok("Account updated successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found").build();
        }
    }

    @DELETE
    @Path("/{accountNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("accountNumber") String accountNumber) {
        boolean deleted = service.deleteAccount(accountNumber);
        if (deleted) {
            return Response.ok("Account deleted successfully").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found").build();
        }
    }
}
