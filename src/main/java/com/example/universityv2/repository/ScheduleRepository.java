package com.example.universityv2.repository;

import com.example.universityv2.entity.LessonEntity;
import com.example.universityv2.entity.ScheduleEntity;
import com.example.universityv2.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByLessonEntity_TeacherEntity(TeacherEntity teacherEntity);
    List<ScheduleEntity> findAllByLessonEntity_GroupEntity_Id(Long id);
    Optional<ScheduleEntity> findByLessonEntity(LessonEntity lessonEntity);
}
