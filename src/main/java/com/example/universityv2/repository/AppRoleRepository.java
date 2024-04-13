package com.example.universityv2.repository;

import com.example.universityv2.entity.AppRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppRoleRepository extends JpaRepository<AppRoleEntity, Long> {
    Optional<AppRoleEntity> getAppRoleEntityByTitle(String title);
}
