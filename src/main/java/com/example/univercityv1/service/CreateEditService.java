package com.example.univercityv1.service;

import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.exception.EmployeeException;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.RoleException;
import com.example.univercityv1.exception.UserNotFoundException;

public interface CreateEditService {
    EmployeeEntity addNewEmployee(String login, String username) throws UserNotFoundException, EmployeeException;

    EmployeeEntity deleteEmployee(String login, String username) throws UserNotFoundException, EmployeeException;

    AppUserEntity giveRoleToUser(String login, String role) throws InvalidCredentialsException, UserNotFoundException, RoleException;

    AppUserEntity deleteUserRole(String login, String roleToDelete) throws InvalidCredentialsException, UserNotFoundException, RoleException;

    EmployeeEntity editEmployeePosition(String login, String position) throws InvalidCredentialsException, UserNotFoundException;

    EmployeeEntity setDepartmentToEmployee(String login, Long departmentId) throws UserNotFoundException;

}
