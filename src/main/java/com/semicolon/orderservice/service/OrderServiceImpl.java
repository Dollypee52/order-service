package com.semicolon.orderservice.service;

import com.semicolon.orderservice.dto.OrderLineItemsDto;
import com.semicolon.orderservice.dto.OrderRequest;
import com.semicolon.orderservice.model.Order;
import com.semicolon.orderservice.model.OrderLineItems;
import com.semicolon.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());

      List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
              .stream()
              .map(this::mapToDto)
              .toList();
      order.setOrderLineItems(orderLineItems);

      orderRepository.save(order);

      return order;
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
