package com.example.universityv2.service;

import com.example.universityv2.dto.request.LessonDtoRequest;
import com.example.universityv2.entity.*;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.LessonException;
import com.example.universityv2.exception.SubjectException;
import com.example.universityv2.exception.UserNotFoundException;

public interface DepartmentHeadService {
    TeacherEntity createNewTeacher(Long employeeId) throws UserNotFoundException;
    TeacherEntity addSubjectToTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException;
    TeacherEntity deleteSubjectFromTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException, SubjectException;
    SpecialityEntity addSubjectToSpeciality(Long specialityId, String subjectName) throws InvalidCredentialsException;
    SpecialityEntity getSpeciality(Long specialityId) throws InvalidCredentialsException;
    StudentEntity studentExpulsion(Long studentId) throws UserNotFoundException;
    LessonEntity createNewLesson(LessonDtoRequest lessonDtoRequest) throws LessonException, InvalidCredentialsException;
}
