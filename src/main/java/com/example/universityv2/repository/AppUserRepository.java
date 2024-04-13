package com.example.universityv2.repository;

import com.example.universityv2.entity.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByLogin(String login);
    Optional<AppUserEntity> findByNameAndSurname(String name, String surname);
    Optional<AppUserEntity> findById(Long id);
}
