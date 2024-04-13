package com.example.universityv2.repository;

import com.example.universityv2.entity.Assignment;
import com.example.universityv2.entity.FinishedAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FinishedAssignmentRepository extends JpaRepository<FinishedAssignment, Long> {
    Optional<List<FinishedAssignment>> findAllByAssignment(Assignment assignment);
}
