package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.AssignmentDtoRequest;
import com.example.univercityv1.dto.request.LessonDtoRequest;
import com.example.univercityv1.dto.response.AssignmentDtoResponse;
import com.example.univercityv1.dto.response.LessonDtoResponse;
import com.example.univercityv1.dto.response.ScheduleDtoResponse;
import com.example.univercityv1.entity.Assignment;
import com.example.univercityv1.entity.LessonEntity;
import com.example.univercityv1.entity.ScheduleEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.LessonException;
import com.example.univercityv1.exception.TeacherException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.LessonMapper;
import com.example.univercityv1.service.DepartmentHeadService;
import com.example.univercityv1.service.StudentService;
import com.example.univercityv1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public LessonDtoResponse createNewLesson(
            @RequestBody LessonDtoRequest lessonDtoRequest
            ) throws LessonException, InvalidCredentialsException {
        return lessonMapper.mapLessonToDtoResponse(departmentHeadService.createNewLesson(lessonDtoRequest));
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value = "/teacher/get/schedule")
    public List<ScheduleDtoResponse> getTeacherSchedule() throws TeacherException {
        List<ScheduleEntity> scheduleEntities = teacherService.getMySchedule();
        return lessonMapper.mapScheduleToDtoResponses(scheduleEntities);
    }

    @PreAuthorize("hasAnyRole('STUDENT')")
    @GetMapping(value = "/student/get/schedule")
    public List<ScheduleDtoResponse> getStudentSchedule() throws TeacherException, UserNotFoundException {
        List<ScheduleEntity> scheduleEntities = studentService.getMyStudentSchedule();
        return lessonMapper.mapScheduleToDtoResponses(scheduleEntities);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/teacher/create/assignment")
    public AssignmentDtoResponse createAssignment(
            @RequestBody AssignmentDtoRequest assignmentDtoRequest
            ) throws LessonException, TeacherException, InvalidCredentialsException {
        Assignment result = teacherService.createNewAssignment(assignmentDtoRequest);
        return lessonMapper.mapAssignmentToDtoResponse(result);
    }
}
