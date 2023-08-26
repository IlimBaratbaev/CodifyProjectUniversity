package com.example.univercityv1.repository;

import com.example.univercityv1.entity.Assignment;
import com.example.univercityv1.entity.FinishedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinishedAssignmentRepository extends JpaRepository<FinishedAssignment, Long> {
    Optional<List<FinishedAssignment>> findAllByAssignment(Assignment assignment);
}
