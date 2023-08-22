package com.example.univercityv1.repository;

import com.example.univercityv1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByAppUserEntity_Login(String login);
}
