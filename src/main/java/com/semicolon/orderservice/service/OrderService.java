package com.semicolon.orderservice.service;

import com.semicolon.orderservice.dto.OrderRequest;
import com.semicolon.orderservice.model.Order;

public interface OrderService {

    Order placeOrder(OrderRequest orderRequest);
}
