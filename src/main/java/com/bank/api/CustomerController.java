package com.bank.api;

import com.bank.dto.CustomerRequest;
import com.bank.model.Customer;
import com.bank.service.CustomerService;
import com.bank.serviceImpl.CustomerServiceImpl;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {

    private final CustomerService service = new CustomerServiceImpl();

    @POST
    public Response createCustomer(CustomerRequest req) {
        try {
            service.onboardCustomer(req);
            return Response.status(Response.Status.CREATED).entity("Customer created").build();
        } catch (IllegalArgumentException ia) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ia.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Server error").build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getCustomer(@PathParam("id") long id) {
        try {
            Customer c = service.getCustomerById(id);
            if (c == null) return Response.status(Response.Status.NOT_FOUND).entity("Not found").build();
            return Response.ok(c).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateCustomer(@PathParam("id") long id, CustomerRequest req) {
        try {
            boolean ok = service.updateCustomer(id, req);
            if (!ok) return Response.status(Response.Status.NOT_FOUND).entity("No such customer").build();
            return Response.ok("Updated").build();
        } catch (IllegalArgumentException ia) {
            return Response.status(Response.Status.BAD_REQUEST).entity(ia.getMessage()).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") long id) {
        try {
            boolean ok = service.deleteCustomer(id);
            if (!ok) return Response.status(Response.Status.NOT_FOUND).entity("No such customer").build();
            return Response.ok("Deleted").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    // optional: list all
    @GET
    public Response listAll() {
        try {
            List<Customer> list = service.getAllCustomers();
            return Response.ok(list).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
