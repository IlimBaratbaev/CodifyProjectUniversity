package com.example.univercityv1.repository;

import com.example.univercityv1.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, Long> {
    Optional<PositionEntity> getPositionEntityByTitle(String title);
}
