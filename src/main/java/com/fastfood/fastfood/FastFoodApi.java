package com.fastfood.fastfood;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fastfood.fastfood.auth.Auth;
import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dao.OrderDAO;
import com.fastfood.fastfood.dto.CheckOrderResponse;
import com.fastfood.fastfood.dto.DishDTO;
import com.fastfood.fastfood.dto.DishResponse;
import com.fastfood.fastfood.dto.MakeOrderRequest;
import com.fastfood.fastfood.dto.PayOrderRequest;
import com.fastfood.fastfood.entity.Dish;
import com.fastfood.fastfood.entity.Order;
import com.fastfood.fastfood.service.MakeOrderService;

@Path("/fast_food")
@Auth
public class FastFoodApi {

    @Inject
    private MakeOrderService makeOrderService;
    @Inject
    private DishDAO dishDAO;
    @Inject
    private OrderDAO orderDAO;

    @GET
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }

    @POST
    @Path("/make_order")
    @Produces("application/json")
    @Consumes("application/json")
    public Response makeOrder(MakeOrderRequest request) throws SQLException {
        return Response.ok(makeOrderService.makeOrder(request)).build();
    }

    @GET
    @Path("/dishes")
    @Produces("application/json")
    public Response getDishes(){
        List<Dish> allDishes = dishDAO.getAllDishes();
        DishResponse response = new DishResponse(dishListToDTO(allDishes));
        return Response.ok(response).build();
    }

    @POST
    @Path("/pay_order")
    @Produces("application/json")
    @Consumes("application/json")
    public Response payOrder(PayOrderRequest request){
        makeOrderService.payOrder(request.getOrder_id(), request.getTotal_sum());
        return Response.ok().build();
    }

    @GET
    @Path("/check")
    @Produces("application/json")
    public Response checkOrderStatus(@QueryParam("id") long id){
        Order order = orderDAO.getOrderById(id);
        if (order == null){
            return Response.status(Status.BAD_REQUEST).build();
        }

        CheckOrderResponse response = new CheckOrderResponse();
        response.setOrder_id(order.getId());
        response.setDishes(dishListToDTO(order.getDishes()));
        response.setStatus(order.getStatus());
        return Response.ok(response).build();
    }

    private List<DishDTO> dishListToDTO(List<Dish> dishes){
        List<DishDTO> dishDTOList = new ArrayList<>();
        for (Dish dish: dishes){
            DishDTO dishDTO = new DishDTO();
            dishDTO.setId(dish.getId());
            dishDTO.setName(dish.getName());
            dishDTO.setPrize(dish.getPrize());
            dishDTO.setImageUrl("http://localhost:8080/FastFood-1.0-SNAPSHOT/" + dish.getImage());
            dishDTOList.add(dishDTO);
        }
        return dishDTOList;
    }
}