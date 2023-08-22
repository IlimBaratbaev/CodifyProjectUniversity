package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.OrderDtoRequest;
import com.example.univercityv1.dto.response.OrderDtoResponse;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.entity.OrdersEntity;
import com.example.univercityv1.exception.OrderException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.OrderMapper;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.repository.EmployeeRepository;
import com.example.univercityv1.repository.OrdersRepository;
import com.example.univercityv1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private final EmployeeRepository employeeRepository;
    private final OrderMapper orderMapper;
    private final OrdersRepository ordersRepository;
    private final AppUserRepository appUserRepository;


    @Autowired
    public OrderServiceImpl(EmployeeRepository employeeRepository, OrderMapper orderMapper, OrdersRepository ordersRepository, AppUserRepository appUserRepository) {
        this.employeeRepository = employeeRepository;
        this.orderMapper = orderMapper;
        this.ordersRepository = ordersRepository;
        this.appUserRepository = appUserRepository;
    }

    @Override
    public OrderDtoResponse sendOrder(OrderDtoRequest orderDtoRequest, EmployeeEntity fromWhom) {
        OrdersEntity ordersEntity = new OrdersEntity()
                .setFromEmployeeEntity(fromWhom)
                .setContent(orderDtoRequest.getContent())
                .setDone(false)
                .setToEmployeeEntity(
                        employeeRepository
                                .findByAppUserEntity_Login(orderDtoRequest.getToWhomLogin()).get());
        ordersRepository.save(ordersEntity);
        return orderMapper.mapOrderEntityToDto(ordersEntity);
    }

    @Override
    public List<OrdersEntity> myOrders(String login) throws OrderException {
        Optional<EmployeeEntity> optionalFromWhom = employeeRepository.findByAppUserEntity_Login(login);
        if (optionalFromWhom.isPresent()) {
            EmployeeEntity fromWhom = optionalFromWhom.get();
            List<OrdersEntity> rectorOrdersEntities = ordersRepository.findAllByToEmployeeEntity(fromWhom);
            return rectorOrdersEntities;
        }
        throw new OrderException("Не удалось получить поручения!");
    }

    @Override
    public OrderDtoResponse redirectOrder(Long orderId, String login) throws UserNotFoundException, OrderException {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(login);
        if (!optionalEmployeeEntity.isPresent()) {
            throw new UserNotFoundException(String.format("Пользователя %s нет в системе!", login));
        }
        Optional<OrdersEntity> optionalRectorOrdersEntity = ordersRepository.findById(orderId);
        if (optionalRectorOrdersEntity.isPresent()) {
            OrdersEntity ordersEntity = optionalRectorOrdersEntity.get();
            ordersEntity.setToEmployeeEntity(optionalEmployeeEntity.get());
            ordersRepository.save(ordersEntity);
            return orderMapper.mapOrderEntityToDto(ordersEntity);
        }
        throw new OrderException("Не получилось перенаправить поручение!");
    }

    @Override
    public OrdersEntity markAsCompleted(Long orderId) throws OrderException {
        Optional<OrdersEntity> optionalOrdersEntity = ordersRepository.findById(orderId);
        if (optionalOrdersEntity.isPresent()) {
            OrdersEntity ordersEntity = optionalOrdersEntity.get();
            ordersEntity.setDone(true);
            ordersRepository.save(ordersEntity);
            return ordersEntity;
        }
        throw new OrderException("Не удалось найти поручение!");
    }
}
