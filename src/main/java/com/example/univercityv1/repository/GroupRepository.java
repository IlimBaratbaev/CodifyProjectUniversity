package com.example.univercityv1.repository;

import com.example.univercityv1.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {
    GroupEntity findBySpecialityEntityId(Long id);
    Optional<List<GroupEntity>> findAllByDepartmentEntityId(Long id);
}
