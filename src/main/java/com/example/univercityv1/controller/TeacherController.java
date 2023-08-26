package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.MarkDtoRequest;
import com.example.univercityv1.dto.response.*;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.*;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.mapper.LessonMapper;
import com.example.univercityv1.service.DepartmentHeadService;
import com.example.univercityv1.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {
    private final EntityMapper entityMapper;
    private final TeacherService teacherService;
    private final LessonMapper lessonMapper;
    @Autowired
    public TeacherController(EntityMapper entityMapper, TeacherService teacherService, LessonMapper lessonMapper, DepartmentHeadService departmentHeadService) {
        this.entityMapper = entityMapper;
        this.teacherService = teacherService;
        this.lessonMapper = lessonMapper;
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
    @DeleteMapping(value ="/delete/subject")
    public TeacherDtoResponse deleteSubjectFromTeacher(
            @RequestParam(name = "teacherId") Long teacherId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException, SubjectException {
        TeacherEntity teacherEntity = departmentHeadService.deleteSubjectFromTeacher(teacherId, subjectName);
        return entityMapper.mapTeacherToDtoResponse(teacherEntity);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/lesson/start")
    public LessonDtoResponse startLesson(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, TeacherException, InvalidCredentialsException {
        LessonEntity lessonEntity = teacherService.startLesson(lessonId);
        return lessonMapper.mapLessonToDtoResponse(lessonEntity);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/lesson/end")
    public LessonDtoResponse endLesson(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, TeacherException, InvalidCredentialsException {
        LessonEntity lessonEntity = teacherService.endLesson(lessonId);
        return lessonMapper.mapLessonToDtoResponse(lessonEntity);
    }
    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="student/attendances/isPresent")
    public List<LessonAttendanceDtoResponse> setAllAttendancesTrue(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.everyoneAttended(lessonId);
        return lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/student/attendance/isPresent")
    public List<LessonAttendanceDtoResponse> setAttendanceTrue(
            @RequestParam(name = "lessonId") Long lessonId,
            @RequestParam(name = "studentId") Long studentId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.studentAttended(lessonId, studentId);
        return lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/student/attendance/notPresent")
    public List<LessonAttendanceDtoResponse> setAttendanceFalse(
            @RequestParam(name = "lessonId") Long lessonId,
            @RequestParam(name = "studentId") Long studentId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.studentNotAttended(lessonId, studentId);
        return lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value ="/student/get/assignments")
    public List<FinishedAssignmentDtoResponse> getFinishedAssignmentsByAssignmentId(
            @RequestParam(name = "assignmentId") Long assignmentId
    ) throws AssignmentException {
        List<FinishedAssignment> finishedAssignments = teacherService.getStudentsFinishedAssignmentsByAssignmentId(assignmentId);
        return lessonMapper.mapFinishedAssignmentsToDtoResponses(finishedAssignments);
    }
    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/student/assignment/set/mark")
    public MarkDtoResponse setMarkToFinishedAssignment(
            @RequestBody MarkDtoRequest markDtoRequest
            ) throws AssignmentException, MarkException {
        MarkEntity markEntity = teacherService.setMarkToFinishedAssignment(markDtoRequest);
        return lessonMapper.mapMarkToDtoResponse(markEntity);
    }
}
