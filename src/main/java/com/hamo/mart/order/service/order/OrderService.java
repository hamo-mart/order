package com.hamo.mart.order.service.order;

import com.hamo.mart.order.dto.order.OrderItemRequest;
import com.hamo.mart.order.dto.order.OrderRequest;
import com.hamo.mart.order.dto.order.OrderResponse;

import java.util.List;

public interface OrderService {


    OrderResponse createOrder(Long userId, OrderRequest orderRequest);

    OrderResponse deleteOrder(String orderNumber);


}
