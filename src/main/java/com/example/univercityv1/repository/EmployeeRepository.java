package com.example.univercityv1.repository;

import com.example.univercityv1.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity> findByAppUserEntity_Login(String login);
}
