package com.example.univercityv1.repository;

import com.example.univercityv1.entity.Assignment;
import com.example.univercityv1.entity.GroupEntity;
import com.example.univercityv1.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByLessonEntity_SubjectEntityAndLessonEntity_GroupEntity(SubjectEntity subjectEntity, GroupEntity groupEntity);
}
