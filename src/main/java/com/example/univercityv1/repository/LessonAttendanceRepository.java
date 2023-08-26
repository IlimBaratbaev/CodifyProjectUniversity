package com.example.univercityv1.repository;

import com.example.univercityv1.entity.LessonAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonAttendanceRepository extends JpaRepository<LessonAttendanceEntity, Long> {
    Optional<List<LessonAttendanceEntity>> findAllByLessonEntityId(Long lessonId);
    Optional<LessonAttendanceEntity> findByLessonEntityIdAndStudentEntityId(Long lessonId, Long studentId);
}
