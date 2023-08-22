package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.OrderDtoRequest;
import com.example.univercityv1.dto.response.OrderDtoResponse;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.exception.OrderException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.OrderMapper;
import com.example.univercityv1.repository.EmployeeRepository;
import com.example.univercityv1.repository.OrdersRepository;
import com.example.univercityv1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    public OrderController(OrderService orderService, EmployeeRepository employeeRepository, OrdersRepository ordersRepository, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.employeeRepository = employeeRepository;
        this.ordersRepository = ordersRepository;
        this.orderMapper = orderMapper;
    }

    private final OrderService orderService;
    private final EmployeeRepository employeeRepository;
    private final OrdersRepository ordersRepository;
    private final OrderMapper orderMapper;


    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/create")
    public OrderDtoResponse createNewOrder(
            @RequestBody OrderDtoRequest orderDtoRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws OrderException {
        String createdBy = userDetails.getUsername();
        System.out.println(createdBy);
        Optional<EmployeeEntity> optionalFromWhom = employeeRepository.findByAppUserEntity_Login(createdBy);
        if (optionalFromWhom.isPresent()) {
            EmployeeEntity fromWhom = optionalFromWhom.get();
            return orderService.sendOrder(orderDtoRequest, fromWhom);
        }
        throw new OrderException("Не удалось создать поручение!");
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "/get")
    public List<OrderDtoResponse> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails
    ) throws OrderException {
        String createdBy = userDetails.getUsername();
        return orderMapper.mapOrderEntitiesToDtoList(orderService.myOrders(createdBy));
    }

    @PreAuthorize("hasAnyRole('RECTOR_SEC', 'DEAN_SEC')")
    @PostMapping(value = "/redirect")
    public OrderDtoResponse redirectOrder(@RequestParam(value = "orderId") Long orderId,
                                          @RequestParam(value = "login") String toWhom) throws UserNotFoundException, OrderException {
        return orderService.redirectOrder(orderId, toWhom);
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "/completed")
    public OrderDtoResponse markAsCompleted(@RequestParam(value = "orderId") Long orderId) throws OrderException {
        return orderMapper.mapOrderEntityToDto(orderService.markAsCompleted(orderId));
    }
}
