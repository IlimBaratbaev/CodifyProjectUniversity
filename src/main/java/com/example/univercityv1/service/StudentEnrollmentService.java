package com.example.univercityv1.service;

import com.example.univercityv1.entity.StudentEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.RoleException;
import com.example.univercityv1.exception.UserNotFoundException;

public interface StudentEnrollmentService {
    StudentEntity enrollStudentToFaculty(String login, Long id) throws InvalidCredentialsException, UserNotFoundException;
    StudentEntity enrollStudentToDepartment(String login, Long id) throws InvalidCredentialsException, UserNotFoundException;
    StudentEntity enrollStudentToSpeciality(String login, Long id) throws InvalidCredentialsException, UserNotFoundException, RoleException;
}
