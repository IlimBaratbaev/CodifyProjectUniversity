package com.example.universityv2.repository;

import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findAllByToEmployeeEntity(EmployeeEntity employeeEntity);
}
