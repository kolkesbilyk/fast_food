package com.fastfood.fastfood.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class MakeOrderRequest {

    @NotEmpty
    private String externalOrderId;
    private List<Integer> dishesId;

    public String getExternalOrderId() {
        return externalOrderId;
    }

    public void setExternalOrderId(String externalOrderId) {
        this.externalOrderId = externalOrderId;
    }

    public List<Integer> getDishesId() {
        return dishesId;
    }

    public void setDishesId(List<Integer> dishesId) {
        this.dishesId = dishesId;
    }
}
