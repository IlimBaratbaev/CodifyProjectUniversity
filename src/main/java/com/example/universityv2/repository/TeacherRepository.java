package com.example.universityv2.repository;

import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByEmployeeEntity_AppUserEntity(AppUserEntity appUserEntity);
}
