package com.example.universityv2.service;

import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.RoleException;
import com.example.universityv2.exception.UserNotFoundException;

public interface StudentEnrollmentService {
    StudentEntity enrollStudentToFaculty(String login, Long id) throws InvalidCredentialsException, UserNotFoundException;
    StudentEntity enrollStudentToDepartment(String login, Long id) throws InvalidCredentialsException, UserNotFoundException;
    StudentEntity enrollStudentToSpeciality(String login, Long id) throws InvalidCredentialsException, UserNotFoundException, RoleException;
}
