package com.example.universityv2.service;

import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.exception.EmployeeException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.RoleException;
import com.example.universityv2.exception.UserNotFoundException;

public interface CreateEditService {
    EmployeeEntity addNewEmployee(String login, String username) throws UserNotFoundException, EmployeeException;

    EmployeeEntity deleteEmployee(String login, String username) throws UserNotFoundException, EmployeeException;

    AppUserEntity giveRoleToUser(String login, String role) throws InvalidCredentialsException, UserNotFoundException, RoleException;

    AppUserEntity deleteUserRole(String login, String roleToDelete) throws InvalidCredentialsException, UserNotFoundException, RoleException;

    EmployeeEntity editEmployeePosition(String login, String position) throws InvalidCredentialsException, UserNotFoundException;

    EmployeeEntity setDepartmentToEmployee(String login, Long departmentId) throws UserNotFoundException;

}
