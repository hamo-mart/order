package com.hamo.mart.order.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String orderNumber) {
        super("Order with number " + orderNumber + " not found.");
    }
}
