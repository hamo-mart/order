package com.hamo.mart.order.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {


    private List<OrderItemRequest> orderItems;
    private Long addressId;

}
