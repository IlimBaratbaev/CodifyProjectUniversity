package com.example.universityv2.service.impl;

import com.example.universityv2.entity.*;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.repository.*;
import com.example.universityv2.service.ViewService;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViewServiceImpl implements ViewService {
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final StudentRepository studentRepository;
    @Autowired
    public ViewServiceImpl(FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SubjectRepository subjectRepository, GroupRepository groupRepository, ExceptionCheckingUtil exceptionCheckingUtil, StudentRepository studentRepository) {
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<FacultyEntity> getAllFaculties() {
        return this.facultyRepository.findAll();
    }

    @Override
    public List<DepartmentEntity> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public List<SubjectEntity> getAllSubjectsByDepartmentId(Long id) {
        return null; //querydsl реализовать
    }

    @Override
    public List<GroupEntity> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<MarkEntity> getStudentPerformanceByGroupId() {
        return null;//querydsl реализовать
    }

    @Override
    public List<ScheduleEntity> getSchedule() {
        return null;//пока нет четкой бд для schedule
    }

    @Override
    public List<GroupEntity> getGroupsByDepartmentId(Long departmentId) throws InvalidCredentialsException {
        Optional<List<GroupEntity>> optionalGroupEntities = groupRepository.findAllByDepartmentEntityId(departmentId);
        exceptionCheckingUtil.checkForPresentOptional(optionalGroupEntities, "Группы", "не найдены!");
        return optionalGroupEntities.get();
    }

    @Override
    public List<StudentEntity> getStudentsByGroupId(Long groupId) throws InvalidCredentialsException {
        Optional<List<StudentEntity>> optionalStudentEntities = studentRepository.findAllByGroupEntityId(groupId);
        exceptionCheckingUtil.checkForPresentOptional(optionalStudentEntities, "Студенты", "не найдены");
        return optionalStudentEntities.get();
    }
}
