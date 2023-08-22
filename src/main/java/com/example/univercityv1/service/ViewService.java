package com.example.univercityv1.service;

import com.example.univercityv1.entity.*;

import java.util.List;

public interface ViewService {
    List<FacultyEntity> getAllFaculties();
    List<DepartmentEntity> getAllDepartments();
    List<SubjectEntity> getAllSubjectsByDepartmentId(Long id);
    List<GroupEntity> getAllGroups();
    List<MarkEntity> getStudentPerformanceByGroupId();
    List<ScheduleEntity> getSchedule();
}
