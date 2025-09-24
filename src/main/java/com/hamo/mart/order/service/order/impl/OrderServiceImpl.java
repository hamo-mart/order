package com.hamo.mart.order.service.order.impl;

import com.hamo.mart.order.domain.Order;
import com.hamo.mart.order.domain.Status;
import com.hamo.mart.order.dto.order.OrderResponse;
import com.hamo.mart.order.exception.OrderNotFoundException;
import com.hamo.mart.order.repository.OrderRepository;
import com.hamo.mart.order.service.order.OrderService;
import com.hamo.mart.order.util.OrderNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    @Override
    public OrderResponse createOrder(Long userId) {

        String orderNumber = OrderNumberGenerator.generateOrderNumber();

        Order save = orderRepository.save(new Order(orderNumber, userId, ZonedDateTime.now(ZoneId.of("Asia/Seoul"))));

        return toResponse(save, Status.STARTED);
    }

    @Transactional
    @Override
    public OrderResponse deleteOrder(String orderNumber) {
        Order order = findOrder(orderNumber);
        orderRepository.delete(order);

        //TODO: 주문삭제시 주문아이템도 함께 삭제
        return toResponse(order, Status.CANCELED);
    }

    private Order findOrder(String orderNumber) {
        return orderRepository.findById(orderNumber).orElseThrow(() -> new OrderNotFoundException(orderNumber));
    }
    private OrderResponse toResponse(Order order, Status status) {
        return new OrderResponse(
                order.getOrderNumber(),
                order.getUserId(),
                status
        );
    }
}
