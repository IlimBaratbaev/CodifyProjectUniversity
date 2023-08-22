package com.example.univercityv1.service.impl;

import com.example.univercityv1.entity.*;
import com.example.univercityv1.repository.DepartmentRepository;
import com.example.univercityv1.repository.FacultyRepository;
import com.example.univercityv1.repository.GroupRepository;
import com.example.univercityv1.repository.SubjectRepository;
import com.example.univercityv1.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ViewServiceImpl implements ViewService {
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    @Autowired
    public ViewServiceImpl(FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SubjectRepository subjectRepository, GroupRepository groupRepository) {
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.subjectRepository = subjectRepository;
        this.groupRepository = groupRepository;
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
}
