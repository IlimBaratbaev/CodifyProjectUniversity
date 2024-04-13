package com.example.universityv2.service;

import com.example.universityv2.dto.request.OrderDtoRequest;
import com.example.universityv2.dto.response.OrderDtoResponse;
import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.entity.OrdersEntity;
import com.example.universityv2.exception.OrderException;
import com.example.universityv2.exception.UserNotFoundException;

import java.util.List;

public interface OrderService {
    OrderDtoResponse sendOrder(OrderDtoRequest orderDtoRequest, EmployeeEntity fromWhom);

    List<OrdersEntity> myOrders(String login) throws OrderException;

    OrderDtoResponse redirectOrder(Long orderId, String login) throws UserNotFoundException, OrderException;

    OrdersEntity markAsCompleted(Long orderId) throws OrderException;

}
