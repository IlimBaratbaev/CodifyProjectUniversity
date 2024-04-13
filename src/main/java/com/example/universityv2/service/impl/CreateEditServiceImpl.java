package com.example.universityv2.service.impl;

import com.example.universityv2.entity.*;
import com.example.universityv2.exception.EmployeeException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.RoleException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.repository.*;
import com.example.universityv2.service.CreateEditService;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CreateEditServiceImpl implements CreateEditService {

    private final AppUserRepository appUserRepository;
    private final EmployeeRepository employeeRepository;
    private final AppRoleRepository appRoleRepository;
    private final PositionRepository positionRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;

    private final DepartmentRepository departmentRepository;

    @Autowired
    public CreateEditServiceImpl(AppUserRepository appUserRepository, EmployeeRepository employeeRepository, AppRoleRepository appRoleRepository, PositionRepository positionRepository, ExceptionCheckingUtil exceptionCheckingUtil, DepartmentRepository departmentRepository) {
        this.appUserRepository = appUserRepository;
        this.employeeRepository = employeeRepository;
        this.appRoleRepository = appRoleRepository;
        this.positionRepository = positionRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public EmployeeEntity addNewEmployee(String login, String username) throws UserNotFoundException, EmployeeException {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(username);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, username);
        EmployeeEntity employeeEntity = new EmployeeEntity();
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(login);
        if (optionalAppUser.isPresent()) {
            AppUserEntity appUserEntity = optionalAppUser.get();
            employeeEntity.setName(appUserEntity.getName())
                    .setSurname(appUserEntity.getSurname())
                    .setAppUserEntity(appUserEntity)
                    .setDepartmentEntity(optionalEmployeeEntity.get().getDepartmentEntity());
            employeeRepository.save(employeeEntity);
            return employeeEntity;
        }
        throw new UserNotFoundException(String.format("Пользователя с логином %s нет в системе.", login));
    }

    @Override
    public EmployeeEntity deleteEmployee(String login, String username) throws UserNotFoundException, EmployeeException {
        Optional<EmployeeEntity> optionalEmployeeEntityForRoleCheck = employeeRepository.findByAppUserEntity_Login(username);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntityForRoleCheck, username);
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, login);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        if (optionalEmployeeEntity.get().getAppUserEntity().getAppRoles().contains("RECTOR")) {
            employeeEntity.setDeleted(true);
            employeeRepository.save(employeeEntity);
            return employeeEntity;
        }
        if (employeeEntity.getDepartmentEntity().equals(optionalEmployeeEntityForRoleCheck.get().getDepartmentEntity())) {
            employeeEntity.setDeleted(true);
            employeeRepository.save(employeeEntity);
            return employeeEntity;
        }
        throw new EmployeeException("Нельзя уволить работника не из своего факультета!");
    }

    @Override
    public AppUserEntity giveRoleToUser(String login, String role) throws InvalidCredentialsException, UserNotFoundException, RoleException {
        exceptionCheckingUtil.checkForEmptiness(login);
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(login);
        exceptionCheckingUtil.checkForPresentUser(optionalAppUser, login);
        AppUserEntity appUserEntity = optionalAppUser.get();
        Optional<AppRoleEntity> optionalAppRoleEntity = appRoleRepository.getAppRoleEntityByTitle(role);
        exceptionCheckingUtil.checkForPresentRole(optionalAppRoleEntity, role);
        AppRoleEntity appRoleEntity = optionalAppRoleEntity.get();
        if (appUserEntity.getAppRoles().contains(appRoleEntity)) {
            throw new RoleException(String.format("Роль: %s есть у пользователя.", role));
        }
        appUserEntity.getAppRoles().add(appRoleRepository.getAppRoleEntityByTitle(role).get());
        appUserRepository.save(appUserEntity);
        appRoleEntity.getAppUserEntities().add(appUserEntity);
        appRoleRepository.save(appRoleEntity);
        return appUserEntity;
    }

    @Override
    public EmployeeEntity editEmployeePosition(String login, String position) throws InvalidCredentialsException, UserNotFoundException {
        exceptionCheckingUtil.checkForEmptiness(login);
        exceptionCheckingUtil.checkForEmptiness(position);
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, login);
        Optional<PositionEntity> optionalPositionEntity = positionRepository.getPositionEntityByTitle(position);
        exceptionCheckingUtil.checkForPresentPosition(optionalPositionEntity, position);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        PositionEntity positionEntity = optionalPositionEntity.get();
        employeeEntity.setPositionEntity(positionEntity);
        employeeRepository.save(employeeEntity);
        return employeeEntity;
    }

    @Override
    public EmployeeEntity setDepartmentToEmployee(String login, Long departmentId) throws UserNotFoundException {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, login);
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        employeeEntity.setDepartmentEntity(departmentRepository.findById(departmentId).get());
        employeeRepository.save(employeeEntity);
        return employeeEntity;
    }

    @Override
    public AppUserEntity deleteUserRole(String login, String roleToDelete) throws InvalidCredentialsException, UserNotFoundException, RoleException {
        exceptionCheckingUtil.checkForEmptiness(login);
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(login);
        exceptionCheckingUtil.checkForPresentUser(optionalAppUser, login);
        AppUserEntity appUserEntity = optionalAppUser.get();
        if (!(Objects.isNull(roleToDelete) || roleToDelete.isEmpty())) {
            Optional<AppRoleEntity> optionalRoleDelete = appRoleRepository.getAppRoleEntityByTitle(roleToDelete);
            if (!optionalRoleDelete.isPresent()) {
                throw new RoleException(String.format("Нет роли %s в системе.", roleToDelete));
            } else {
                if (appUserEntity.getAppRoles().contains(optionalRoleDelete.get())) {
                    appUserEntity.removeAppRole(optionalRoleDelete.get());
                    appUserRepository.save(appUserEntity);
//                    appRoleRepository.delete(optionalRoleDelete.get());
                } else {
                    throw new RoleException(String.format("У пользователя %s нет роли %s.", appUserEntity.getLogin(), roleToDelete));
                }
            }
        }
        return appUserEntity;
    }

}
