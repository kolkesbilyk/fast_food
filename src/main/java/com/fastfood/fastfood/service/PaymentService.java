package com.fastfood.fastfood.service;

import java.io.Serializable;

public interface PaymentService extends Serializable {

    void makePay(long orderID, double totalSum);

}
