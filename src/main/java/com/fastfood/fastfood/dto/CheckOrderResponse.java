package com.fastfood.fastfood.dto;

import java.util.List;

import com.fastfood.fastfood.entity.Order.Status;

public class CheckOrderResponse {
    private long order_id;
    private List<DishDTO> dishes;
    private Status status;

    public long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(long order_id) {
        this.order_id = order_id;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
