package com.fastfood.fastfood.service;

import java.io.Serializable;
import java.sql.SQLException;

import com.fastfood.fastfood.dto.MakeOrderRequest;
import com.fastfood.fastfood.dto.MakeOrderResponse;

public interface MakeOrderService extends Serializable {
    MakeOrderResponse makeOrder(MakeOrderRequest request) throws SQLException;
    void payOrder(long order_id, double totalSum);
}
