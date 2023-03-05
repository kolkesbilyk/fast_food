package com.fastfood.fastfood;

import java.util.List;

public class MakeOrderRequest {
    private String orderId;
    private List<Integer> dishesId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Integer> getDishesId() {
        return dishesId;
    }

    public void setDishesId(List<Integer> dishesId) {
        this.dishesId = dishesId;
    }
}
