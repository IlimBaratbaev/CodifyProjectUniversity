package com.example.univercityv1.repository;

import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long> {
    Optional<TeacherEntity> findByEmployeeEntity_AppUserEntity(AppUserEntity appUserEntity);
}
