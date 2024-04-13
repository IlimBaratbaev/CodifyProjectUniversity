package com.example.universityv2.controller;

import com.example.universityv2.dto.request.OrderDtoRequest;
import com.example.universityv2.dto.response.OrderDtoResponse;
import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.exception.OrderException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.OrderMapper;
import com.example.universityv2.repository.EmployeeRepository;
import com.example.universityv2.repository.OrdersRepository;
import com.example.universityv2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<OrderDtoResponse> createNewOrder(
            @RequestBody OrderDtoRequest orderDtoRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws OrderException {
        String createdBy = userDetails.getUsername();
        System.out.println(createdBy);
        Optional<EmployeeEntity> optionalFromWhom = employeeRepository.findByAppUserEntity_Login(createdBy);
        if (optionalFromWhom.isPresent()) {
            EmployeeEntity fromWhom = optionalFromWhom.get();
            return new ResponseEntity<>(orderService.sendOrder(orderDtoRequest, fromWhom), HttpStatus.CREATED);
        }
        throw new OrderException("Не удалось создать поручение!");
    }

    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @GetMapping(value = "/get")
    public ResponseEntity<List<OrderDtoResponse>> getMyOrders(
            @AuthenticationPrincipal UserDetails userDetails
    ) throws OrderException {
        String createdBy = userDetails.getUsername();
        return new ResponseEntity<>(orderMapper.mapOrderEntitiesToDtoList(orderService.myOrders(createdBy)), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR_SEC', 'DEAN_SEC')")
    @PostMapping(value = "/redirect")
    public ResponseEntity<OrderDtoResponse> redirectOrder(@RequestParam(value = "orderId") Long orderId,
                                          @RequestParam(value = "login") String toWhom) throws UserNotFoundException, OrderException {
        return new ResponseEntity<>(orderService.redirectOrder(orderId, toWhom), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('EMPLOYEE')")
    @PostMapping(value = "/completed")
    public ResponseEntity<OrderDtoResponse> markAsCompleted(@RequestParam(value = "orderId") Long orderId) throws OrderException {
        return new ResponseEntity<>(orderMapper.mapOrderEntityToDto(orderService.markAsCompleted(orderId)), HttpStatus.OK);
    }
}
