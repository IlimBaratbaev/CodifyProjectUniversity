package com.example.universityv2.repository;

import com.example.universityv2.entity.LessonAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonAttendanceRepository extends JpaRepository<LessonAttendanceEntity, Long> {
    Optional<List<LessonAttendanceEntity>> findAllByLessonEntityId(Long lessonId);
    Optional<LessonAttendanceEntity> findByLessonEntityIdAndStudentEntityId(Long lessonId, Long studentId);
}
