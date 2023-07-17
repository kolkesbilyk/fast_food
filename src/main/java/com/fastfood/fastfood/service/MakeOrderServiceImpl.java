package com.fastfood.fastfood.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.fastfood.fastfood.dto.MakeOrderRequest;
import com.fastfood.fastfood.dto.MakeOrderResponse;
import com.fastfood.fastfood.dao.DishDAO;
import com.fastfood.fastfood.dao.OrderDAO;
import com.fastfood.fastfood.entity.Dish;
import com.fastfood.fastfood.entity.Order;

public class MakeOrderServiceImpl implements MakeOrderService{

    @Inject
    private DishDAO dishDAO;
    @Inject
    private OrderDAO orderDAO;
    @Inject
    private PaymentService paymentService;

    @Override
    public MakeOrderResponse makeOrder(MakeOrderRequest request) throws SQLException {
        double total_sum = 0;
        List<Dish> dishes = new ArrayList<>();
        for (Integer id : request.getDishesId()){
            Dish dish = dishDAO.getDishById(id);
            dishes.add(dish);
            total_sum += dish.getPrize();
        }
        Order order = new Order();
        order.setDishes(dishes);
        order.setTotalSum(total_sum);
        order.setDopInfo(request.getExternalOrderId());
        orderDAO.saveOrderDishes(order);

        paymentService.makePay(order.getId(), total_sum);

        MakeOrderResponse response = new MakeOrderResponse();
        response.setOrder_id(order.getId());
        response.setTotal_sum(order.getTotalSum());
        return response;
    }
}
