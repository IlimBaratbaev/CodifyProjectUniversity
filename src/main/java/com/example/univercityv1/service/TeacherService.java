package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.AssignmentDtoRequest;
import com.example.univercityv1.dto.request.MarkDtoRequest;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.*;

import java.util.List;

public interface TeacherService {
    List<ScheduleEntity> getMySchedule() throws TeacherException;
    LessonEntity startLesson(Long lessonId) throws TeacherException, LessonException, InvalidCredentialsException;
    LessonEntity endLesson(Long lessonId) throws TeacherException, LessonException, InvalidCredentialsException;
    List<LessonAttendanceEntity> everyoneAttended(Long lessonId) throws LessonException, InvalidCredentialsException;
    List<LessonAttendanceEntity> studentNotAttended(Long lessonId, Long studentId) throws InvalidCredentialsException;
    List<LessonAttendanceEntity> studentAttended(Long lessonId, Long studentId) throws InvalidCredentialsException;
    Assignment createNewAssignment(AssignmentDtoRequest assignmentDtoRequest) throws TeacherException, InvalidCredentialsException, LessonException;
    List<FinishedAssignment> getStudentsFinishedAssignmentsByAssignmentId(Long assignmentId) throws AssignmentException;
    MarkEntity setMarkToFinishedAssignment(MarkDtoRequest markDtoRequest) throws AssignmentException, MarkException;
}
