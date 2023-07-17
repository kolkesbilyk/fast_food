package com.fastfood.fastfood.dto;

import java.util.List;

public class DishResponse {

    private final List<DishDTO> dishes;

    public DishResponse(List<DishDTO> dishes) {
        this.dishes = dishes;
    }

    public List<DishDTO> getDishes() {
        return dishes;
    }
}
