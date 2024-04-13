package com.example.universityv2.repository;

import com.example.universityv2.entity.Assignment;
import com.example.universityv2.entity.GroupEntity;
import com.example.universityv2.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findAllByLessonEntity_SubjectEntityAndLessonEntity_GroupEntity(SubjectEntity subjectEntity, GroupEntity groupEntity);
}
