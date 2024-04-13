package com.example.universityv2.repository;

import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.entity.GroupEntity;
import com.example.universityv2.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    Optional<StudentEntity> findByAppUserEntity_Login(String login);
    Optional<StudentEntity> findByAppUserEntity(AppUserEntity appUserEntity);
    Optional<List<StudentEntity>> findAllByGroupEntityId(Long id);
    List<StudentEntity> findAllByGroupEntity(GroupEntity groupEntity);
}
