package com.example.univercityv1.repository;

import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    List<OrdersEntity> findAllByToEmployeeEntity(EmployeeEntity employeeEntity);
}
