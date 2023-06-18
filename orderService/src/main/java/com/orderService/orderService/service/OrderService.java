package com.orderService.orderService.service;

import com.orderService.orderService.entity.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public Order saveOrder(Order order);
}
