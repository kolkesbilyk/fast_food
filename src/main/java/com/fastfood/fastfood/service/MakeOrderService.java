package com.fastfood.fastfood.service;

import java.sql.SQLException;

import com.fastfood.fastfood.dto.MakeOrderRequest;
import com.fastfood.fastfood.dto.MakeOrderResponse;

public interface MakeOrderService {
    MakeOrderResponse makeOrder(MakeOrderRequest request) throws SQLException;
}
