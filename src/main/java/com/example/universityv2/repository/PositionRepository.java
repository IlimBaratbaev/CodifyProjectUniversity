package com.example.universityv2.repository;

import com.example.universityv2.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    Optional<PositionEntity> getPositionEntityByTitle(String title);
}
