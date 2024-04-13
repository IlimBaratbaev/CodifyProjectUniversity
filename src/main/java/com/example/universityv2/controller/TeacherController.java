package com.example.universityv2.controller;

import com.example.universityv2.dto.request.MarkDtoRequest;
import com.example.universityv2.dto.response.*;
import com.example.universityv2.entity.*;
import com.example.universityv2.exception.*;
import com.example.universityv2.mapper.EntityMapper;
import com.example.universityv2.mapper.LessonMapper;
import com.example.universityv2.service.DepartmentHeadService;
import com.example.universityv2.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TeacherDtoResponse> createNewTeacher(
            @RequestParam(name = "employeeId") Long employeeId
    ) throws UserNotFoundException {
        TeacherEntity teacherEntity = departmentHeadService.createNewTeacher(employeeId);
        return new ResponseEntity<>(entityMapper.mapTeacherToDtoResponse(teacherEntity), HttpStatus.CREATED);
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/add/subject")
    public ResponseEntity<TeacherDtoResponse> addSubjectToTeacher(
            @RequestParam(name = "teacherId") Long teacherId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException {
        TeacherEntity teacherEntity = departmentHeadService.addSubjectToTeacher(teacherId, subjectName);
        return new ResponseEntity<>(entityMapper.mapTeacherToDtoResponse(teacherEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @DeleteMapping(value ="/delete/subject")
    public ResponseEntity<TeacherDtoResponse> deleteSubjectFromTeacher(
            @RequestParam(name = "teacherId") Long teacherId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException, SubjectException {
        TeacherEntity teacherEntity = departmentHeadService.deleteSubjectFromTeacher(teacherId, subjectName);
        return new ResponseEntity<>(entityMapper.mapTeacherToDtoResponse(teacherEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/lesson/start")
    public ResponseEntity<LessonDtoResponse> startLesson(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, TeacherException, InvalidCredentialsException {
        LessonEntity lessonEntity = teacherService.startLesson(lessonId);
        return new ResponseEntity<>(lessonMapper.mapLessonToDtoResponse(lessonEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/lesson/end")
    public ResponseEntity<LessonDtoResponse> endLesson(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, TeacherException, InvalidCredentialsException {
        LessonEntity lessonEntity = teacherService.endLesson(lessonId);
        return new ResponseEntity<>(lessonMapper.mapLessonToDtoResponse(lessonEntity), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="student/attendances/isPresent")
    public ResponseEntity<List<LessonAttendanceDtoResponse>> setAllAttendancesTrue(
            @RequestParam(name = "lessonId") Long lessonId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.everyoneAttended(lessonId);
        return new ResponseEntity<>(lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/student/attendance/isPresent")
    public ResponseEntity<List<LessonAttendanceDtoResponse>> setAttendanceTrue(
            @RequestParam(name = "lessonId") Long lessonId,
            @RequestParam(name = "studentId") Long studentId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.studentAttended(lessonId, studentId);
        return new ResponseEntity<>(lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value ="/student/attendance/notPresent")
    public ResponseEntity<List<LessonAttendanceDtoResponse>> setAttendanceFalse(
            @RequestParam(name = "lessonId") Long lessonId,
            @RequestParam(name = "studentId") Long studentId
    ) throws LessonException, InvalidCredentialsException {
        List<LessonAttendanceEntity> lessonAttendanceEntities = teacherService.studentNotAttended(lessonId, studentId);
        return new ResponseEntity<>(lessonMapper.mapAttendancesToDtoResponses(lessonAttendanceEntities), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('TEACHER')")
    @GetMapping(value ="/student/get/assignments")
    public ResponseEntity<List<FinishedAssignmentDtoResponse>> getFinishedAssignmentsByAssignmentId(
            @RequestParam(name = "assignmentId") Long assignmentId
    ) throws AssignmentException {
        List<FinishedAssignment> finishedAssignments = teacherService.getStudentsFinishedAssignmentsByAssignmentId(assignmentId);
        return new ResponseEntity<>(lessonMapper.mapFinishedAssignmentsToDtoResponses(finishedAssignments), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('TEACHER')")
    @PostMapping(value = "/student/assignment/set/mark")
    public ResponseEntity<MarkDtoResponse> setMarkToFinishedAssignment(
            @RequestBody MarkDtoRequest markDtoRequest
            ) throws AssignmentException, MarkException {
        MarkEntity markEntity = teacherService.setMarkToFinishedAssignment(markDtoRequest);
        return new ResponseEntity<>(lessonMapper.mapMarkToDtoResponse(markEntity), HttpStatus.OK);
    }
}
