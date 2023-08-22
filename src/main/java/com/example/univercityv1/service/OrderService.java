package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.OrderDtoRequest;
import com.example.univercityv1.dto.response.OrderDtoResponse;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.entity.OrdersEntity;
import com.example.univercityv1.exception.OrderException;
import com.example.univercityv1.exception.UserNotFoundException;

import java.util.List;

public interface OrderService {
    OrderDtoResponse sendOrder(OrderDtoRequest orderDtoRequest, EmployeeEntity fromWhom);

    List<OrdersEntity> myOrders(String login) throws OrderException;

    OrderDtoResponse redirectOrder(Long orderId, String login) throws UserNotFoundException, OrderException;

    OrdersEntity markAsCompleted(Long orderId) throws OrderException;

}
