package com.example.univercityv1.repository;

import com.example.univercityv1.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findBySpecialityEntityId(Long id);
}
