package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.LessonDtoRequest;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.LessonException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;

import java.util.List;

public interface DepartmentHeadService {
    TeacherEntity createNewTeacher(Long employeeId) throws UserNotFoundException;
    TeacherEntity addSubjectToTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException;
    TeacherEntity deleteSubjectFromTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException, SubjectException;
    SpecialityEntity addSubjectToSpeciality(Long specialityId, String subjectName) throws InvalidCredentialsException;
    SpecialityEntity getSpeciality(Long specialityId) throws InvalidCredentialsException;
    StudentEntity studentExpulsion(Long studentId) throws UserNotFoundException;
    LessonEntity createNewLesson(LessonDtoRequest lessonDtoRequest) throws LessonException, InvalidCredentialsException;
}
