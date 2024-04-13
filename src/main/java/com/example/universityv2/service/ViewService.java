package com.example.universityv2.service;

import com.example.universityv2.entity.*;
import com.example.universityv2.exception.InvalidCredentialsException;

import java.util.List;

public interface ViewService {
    List<FacultyEntity> getAllFaculties();
    List<DepartmentEntity> getAllDepartments();
    List<SubjectEntity> getAllSubjectsByDepartmentId(Long id);
    List<GroupEntity> getAllGroups();
    List<MarkEntity> getStudentPerformanceByGroupId();
    List<ScheduleEntity> getSchedule();
    List<GroupEntity> getGroupsByDepartmentId(Long departmentId) throws InvalidCredentialsException;
    List<StudentEntity> getStudentsByGroupId(Long groupId) throws InvalidCredentialsException;
}
