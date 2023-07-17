package com.fastfood.fastfood.service;

public class DefaultPaymentService implements PaymentService{

    @Override
    public void makePay(long orderID, double totalSum) {
        try {
            Thread.sleep(1000);
            System.out.println("payment for order: " + orderID + " on sum: " + totalSum);
        } catch (InterruptedException e) {
            System.out.println("Error payment for order: " + orderID + " on sum: " + totalSum + " cause: " + e.getMessage());
        }
    }
}
