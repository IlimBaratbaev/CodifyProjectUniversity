package com.example.univercityv1.service;

import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.entity.SpecialityEntity;
import com.example.univercityv1.entity.SubjectEntity;
import com.example.univercityv1.entity.TeacherEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;

import java.util.List;

public interface DepartmentHeadService {
    TeacherEntity createNewTeacher(Long employeeId) throws UserNotFoundException;
    TeacherEntity addSubjectToTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException;
    TeacherEntity deleteSubjectFromTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException, SubjectException;
    SpecialityEntity addSubjectToSpeciality(Long specialityId, String subjectName) throws InvalidCredentialsException;
    SpecialityEntity getSpeciality(Long specialityId) throws InvalidCredentialsException;
}
