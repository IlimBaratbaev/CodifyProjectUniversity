package com.example.univercityv1.controller;

import com.example.univercityv1.dto.response.TeacherDtoResponse;
import com.example.univercityv1.entity.TeacherEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.service.DepartmentHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final EntityMapper entityMapper;
    @Autowired
    public TeacherController(EntityMapper entityMapper, DepartmentHeadService departmentHeadService) {
        this.entityMapper = entityMapper;
        this.departmentHeadService = departmentHeadService;
    }
    private final DepartmentHeadService departmentHeadService;
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/create")
    public TeacherDtoResponse createNewTeacher(
            @RequestParam(name = "employeeId") Long employeeId
    ) throws UserNotFoundException {
        TeacherEntity teacherEntity = departmentHeadService.createNewTeacher(employeeId);
        return entityMapper.mapTeacherToDtoResponse(teacherEntity);
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/add/subject")
    public TeacherDtoResponse addSubjectToTeacher(
            @RequestParam(name = "teacherId") Long teacherId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException {
        TeacherEntity teacherEntity = departmentHeadService.addSubjectToTeacher(teacherId, subjectName);
        return entityMapper.mapTeacherToDtoResponse(teacherEntity);
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @DeleteMapping("delete/subject")
    public TeacherDtoResponse deleteSubjectFromTeacher(
            @RequestParam(name = "teacherId") Long teacherId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException, SubjectException {
        TeacherEntity teacherEntity = departmentHeadService.deleteSubjectFromTeacher(teacherId, subjectName);
        return entityMapper.mapTeacherToDtoResponse(teacherEntity);
    }


}
