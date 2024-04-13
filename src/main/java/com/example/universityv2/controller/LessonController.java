package com.example.universityv2.controller;

import com.example.universityv2.dto.request.AssignmentDtoRequest;
import com.example.universityv2.dto.request.LessonDtoRequest;
import com.example.universityv2.dto.response.AssignmentDtoResponse;
import com.example.universityv2.dto.response.LessonDtoResponse;
import com.example.universityv2.dto.response.ScheduleDtoResponse;
import com.example.universityv2.entity.Assignment;
import com.example.universityv2.entity.ScheduleEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.LessonException;
import com.example.universityv2.exception.TeacherException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.LessonMapper;
import com.example.universityv2.service.DepartmentHeadService;
import com.example.universityv2.service.StudentService;
import com.example.universityv2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/lesson")
public class LessonController {
    private final DepartmentHeadService departmentHeadService;
    private final TeacherService teacherService;
    private final LessonMapper lessonMapper;
    private final StudentService studentService;

    @Autowired
    public LessonController(DepartmentHeadService departmentHeadService, TeacherService teacherService, LessonMapper lessonMapper, StudentService studentService) {
        this.departmentHeadService = departmentHeadService;
        this.teacherService = teacherService;
        this.lessonMapper = lessonMapper;
        this.studentService = studentService;
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/create")
    public ResponseEntity<LessonDtoResponse> createNewLesson(
            @RequestBody LessonDtoRequest lessonDtoRequest
            ) throws LessonException, InvalidCredentialsException {
        return new ResponseEntity<>(lessonMapper.mapLessonToDtoResponse(departmentHeadService.createNewLesson(lessonDtoRequest)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value = "/teacher/get/schedule")
    public ResponseEntity<List<ScheduleDtoResponse>> getTeacherSchedule() throws TeacherException {
        List<ScheduleEntity> scheduleEntities = teacherService.getMySchedule();
        return new ResponseEntity<>(lessonMapper.mapScheduleToDtoResponses(scheduleEntities), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/student/get/schedule")
    public ResponseEntity<List<ScheduleDtoResponse>> getStudentSchedule() throws TeacherException, UserNotFoundException {
        List<ScheduleEntity> scheduleEntities = studentService.getMyStudentSchedule();
        return new ResponseEntity<>(lessonMapper.mapScheduleToDtoResponses(scheduleEntities), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/teacher/create/assignment")
    public ResponseEntity<AssignmentDtoResponse> createAssignment(
            @RequestBody AssignmentDtoRequest assignmentDtoRequest
            ) throws LessonException, TeacherException, InvalidCredentialsException {
        Assignment result = teacherService.createNewAssignment(assignmentDtoRequest);
        return new ResponseEntity<>(lessonMapper.mapAssignmentToDtoResponse(result), HttpStatus.CREATED);
    }
}
