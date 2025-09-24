package com.hamo.mart.order.service.order.impl;

import com.hamo.mart.order.domain.Order;
import com.hamo.mart.order.domain.OrderItem;
import com.hamo.mart.order.domain.Status;
import com.hamo.mart.order.dto.order.OrderItemRequest;
import com.hamo.mart.order.dto.order.OrderRequest;
import com.hamo.mart.order.dto.order.OrderResponse;
import com.hamo.mart.order.exception.OrderNotFoundException;
import com.hamo.mart.order.repository.OrderItemRepository;
import com.hamo.mart.order.repository.OrderRepository;
import com.hamo.mart.order.service.order.OrderService;
import com.hamo.mart.order.util.OrderNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public OrderResponse createOrder(Long userId, OrderRequest orderRequest) {

        String orderNumber = OrderNumberGenerator.generateOrderNumber();

        Order save = orderRepository.save(
                new Order(
                        orderNumber,
                        userId,
                        ZonedDateTime.now(ZoneId.of("Asia/Seoul")),
                        orderRequest.getAddressId()
                ));
        for (OrderItemRequest item : orderRequest.getOrderItems()) {
            orderItemRepository.save(new OrderItem(
                    save,
                    item.getItemId(),
                    item.getQuantity(),
                    Status.PENDING
            ));
        }

        //TODO: 주문생성 이벤트 발행(kafka)



        return toResponse(save, Status.PENDING);
    }

    @Transactional
    @Override
    public OrderResponse deleteOrder(String orderNumber) {
        Order order = findOrder(orderNumber);
        orderRepository.delete(order);

        //TODO: 주문삭제시 주문아이템도 함께 삭제
        return toResponse(order, Status.CANCELLED);
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
