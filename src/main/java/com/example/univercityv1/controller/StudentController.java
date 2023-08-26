package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.FinishedAssignmentDtoRequest;
import com.example.univercityv1.dto.response.AssignmentDtoResponse;
import com.example.univercityv1.dto.response.FinishedAssignmentDtoResponse;
import com.example.univercityv1.dto.response.StudentDtoResponse;
import com.example.univercityv1.entity.Assignment;
import com.example.univercityv1.entity.FinishedAssignment;
import com.example.univercityv1.entity.StudentEntity;
import com.example.univercityv1.exception.AssignmentException;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.mapper.LessonMapper;
import com.example.univercityv1.service.DepartmentHeadService;
import com.example.univercityv1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final DepartmentHeadService departmentHeadService;
    private final StudentService studentService;
    private final EntityMapper entityMapper;
    private final LessonMapper lessonMapper;

    @Autowired
    public StudentController(DepartmentHeadService departmentHeadService, StudentService studentService, EntityMapper entityMapper, LessonMapper lessonMapper) {
        this.departmentHeadService = departmentHeadService;
        this.studentService = studentService;
        this.entityMapper = entityMapper;
        this.lessonMapper = lessonMapper;
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/delete")
    public StudentDtoResponse expulseStudent(
            @RequestParam(name = "studentId") Long studentId
    ) throws UserNotFoundException {
        StudentEntity studentEntity = departmentHeadService.studentExpulsion(studentId);
        return entityMapper.mapStudentToDtoResponse(studentEntity);
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/get/assignments")
    public List<AssignmentDtoResponse> getAssignmentsForSubject(
            @RequestParam(name = "subjectName") String subjectName
    ) throws UserNotFoundException, InvalidCredentialsException, SubjectException {
        List<Assignment> assignmentList = studentService.getMyAssignmentsBySubjectName(subjectName);
        return lessonMapper.mapAssignmentsToDtoResponses(assignmentList);
    }
    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping(value = "/send/assignment")
    public FinishedAssignmentDtoResponse sendFinishedAssignment(
            @RequestBody FinishedAssignmentDtoRequest finishedAssignmentDtoRequest
    ) throws UserNotFoundException, AssignmentException {
        FinishedAssignment finishedAssignment = studentService.sendAssignment(finishedAssignmentDtoRequest);
        return lessonMapper.mapFinishedAssignmentToDtoResponse(finishedAssignment);
    }

}
