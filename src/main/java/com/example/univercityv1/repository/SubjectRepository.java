package com.example.univercityv1.repository;

import com.example.univercityv1.entity.SpecialityEntity;
import com.example.univercityv1.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    Optional<SubjectEntity> findByTitle(String subjectName);
}
