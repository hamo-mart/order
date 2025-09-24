package com.hamo.mart.order.dto.order;

import com.hamo.mart.order.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderResponse {

    private String orderNumber;
    private Long userId;
    private Status status;
}
