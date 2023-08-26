package com.example.univercityv1.repository;

import com.example.univercityv1.entity.LessonEntity;
import com.example.univercityv1.entity.ScheduleEntity;
import com.example.univercityv1.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByLessonEntity_TeacherEntity(TeacherEntity teacherEntity);
    List<ScheduleEntity> findAllByLessonEntity_GroupEntity_Id(Long id);
    Optional<ScheduleEntity> findByLessonEntity(LessonEntity lessonEntity);
}
