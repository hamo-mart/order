package com.hamo.mart.order.service.order;

import com.hamo.mart.order.dto.order.OrderRequest;
import com.hamo.mart.order.dto.order.OrderResponse;

public interface OrderService {


    OrderResponse createOrder(Long userId);

    OrderResponse deleteOrder(String orderNumber);


}
