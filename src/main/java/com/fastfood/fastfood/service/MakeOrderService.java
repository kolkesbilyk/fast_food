package com.fastfood.fastfood.service;

import java.sql.SQLException;

import com.fastfood.fastfood.MakeOrderRequest;
import com.fastfood.fastfood.MakeOrderResponse;

public interface MakeOrderService {
    MakeOrderResponse makeOrder(MakeOrderRequest request) throws SQLException;
}
