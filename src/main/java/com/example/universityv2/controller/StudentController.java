package com.example.universityv2.controller;

import com.example.universityv2.dto.request.FinishedAssignmentDtoRequest;
import com.example.universityv2.dto.response.AssignmentDtoResponse;
import com.example.universityv2.dto.response.FinishedAssignmentDtoResponse;
import com.example.universityv2.dto.response.StudentDtoResponse;
import com.example.universityv2.entity.Assignment;
import com.example.universityv2.entity.FinishedAssignment;
import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.AssignmentException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.SubjectException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.EntityMapper;
import com.example.universityv2.mapper.LessonMapper;
import com.example.universityv2.service.DepartmentHeadService;
import com.example.universityv2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<StudentDtoResponse> expulseStudent(
            @RequestParam(name = "studentId") Long studentId
    ) throws UserNotFoundException {
        StudentEntity studentEntity = departmentHeadService.studentExpulsion(studentId);
        return new ResponseEntity<>(entityMapper.mapStudentToDtoResponse(studentEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/get/assignments")
    public ResponseEntity<List<AssignmentDtoResponse>> getAssignmentsForSubject(
            @RequestParam(name = "subjectName") String subjectName
    ) throws UserNotFoundException, InvalidCredentialsException, SubjectException {
        List<Assignment> assignmentList = studentService.getMyAssignmentsBySubjectName(subjectName);
        return new ResponseEntity<>(lessonMapper.mapAssignmentsToDtoResponses(assignmentList), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('STUDENT')")
    @PostMapping(value = "/send/assignment")
    public ResponseEntity<FinishedAssignmentDtoResponse> sendFinishedAssignment(
            @RequestBody FinishedAssignmentDtoRequest finishedAssignmentDtoRequest
    ) throws UserNotFoundException, AssignmentException {
        FinishedAssignment finishedAssignment = studentService.sendAssignment(finishedAssignmentDtoRequest);
        return new ResponseEntity<>(lessonMapper.mapFinishedAssignmentToDtoResponse(finishedAssignment), HttpStatus.OK);
    }

}
