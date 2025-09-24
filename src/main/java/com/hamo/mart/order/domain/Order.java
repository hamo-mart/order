package com.hamo.mart.order.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order {

    @Id
    @Column(name = "order_number", nullable = false)
    private String orderNumber;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "order_date", nullable = false)
    private ZonedDateTime orderDate;

    @Column(name = "address_id", nullable = false)
    private Long addressId;

    public Order(String orderNumber, Long userId, ZonedDateTime orderDate, Long addressId) {
        this.orderNumber = orderNumber;
        this.userId = userId;
        this.orderDate = orderDate;
        this.addressId = addressId;
    }

}
