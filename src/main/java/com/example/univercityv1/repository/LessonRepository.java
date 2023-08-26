package com.example.univercityv1.repository;

import com.example.univercityv1.entity.LessonEntity;
import com.example.univercityv1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<LessonEntity, Long> {

}
