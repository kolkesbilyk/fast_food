package com.fastfood.fastfood;

import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.fastfood.fastfood.service.MakeOrderService;

@Path("/make_order")
public class HelloResource {

    @Inject
    private MakeOrderService makeOrderService;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response makeOrder(MakeOrderRequest request) throws SQLException {
        return Response.ok(makeOrderService.makeOrder(request)).build();
    }
}