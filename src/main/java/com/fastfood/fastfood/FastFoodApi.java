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
import javax.ws.rs.core.Response;

import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dto.DishDTO;
import com.fastfood.fastfood.dto.DishResponse;
import com.fastfood.fastfood.dto.MakeOrderRequest;
import com.fastfood.fastfood.entity.Dish;
import com.fastfood.fastfood.service.MakeOrderService;

@Path("/fast_food")
public class FastFoodApi {

    @Inject
    private MakeOrderService makeOrderService;
    @Inject
    private DishDAO dishDAO;

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
        List<DishDTO> dishDTOList = new ArrayList<>();
        List<Dish> allDishes = dishDAO.getAllDishes();
        for (Dish dish: allDishes){
            DishDTO dishDTO = new DishDTO();
            dishDTO.setId(dish.getId());
            dishDTO.setName(dish.getName());
            dishDTO.setPrize(dish.getPrize());
            dishDTO.setImageUrl("http://localhost:8080/FastFood-1.0-SNAPSHOT/" + dish.getImage());
            dishDTOList.add(dishDTO);
        }
        DishResponse response = new DishResponse(dishDTOList);
        return Response.ok(response).build();
    }
}