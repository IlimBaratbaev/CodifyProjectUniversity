package com.example.universityv2.controller;

import com.example.universityv2.dto.response.*;
import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.entity.GroupEntity;
import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.EntityMapper;
import com.example.universityv2.repository.AppUserRepository;
import com.example.universityv2.service.impl.ViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/view")
public class ViewController {
    private final ViewServiceImpl viewService;
    private final EntityMapper entityMapper;
    private final AppUserRepository appUserRepository;

    @Autowired
    public ViewController(ViewServiceImpl view, EntityMapper entityMapper, AppUserRepository appUserRepository) {
        this.viewService = view;
        this.entityMapper = entityMapper;
        this.appUserRepository = appUserRepository;
    }

    @GetMapping(value = "/all/faculties")
    public ResponseEntity<List<FacultyDtoResponse>> getAllFaculties() {
        return new ResponseEntity<>(entityMapper.mapFacultyToDtoList(viewService.getAllFaculties()), HttpStatus.OK);
    }

    @GetMapping(value = "/all/departments")
    public ResponseEntity<List<DepartmentDtoResponse>> getAllDepartments() {
        return new ResponseEntity<>(entityMapper.mapDepartmentToDtoList(viewService.getAllDepartments()), HttpStatus.OK);
    }

    @GetMapping(value = "/all/groups")
    public ResponseEntity<List<GroupDtoResponse>> getAllGroups() {
        return new ResponseEntity<>(entityMapper.mapGroupToDtoList(viewService.getAllGroups()), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECTOR')")
    @GetMapping(value = "/all/user/roles")
    public ResponseEntity<AppUserDtoResponse> getUserInformation(
            @RequestParam String login
    ) throws UserNotFoundException {
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(login);
        if (optionalAppUser.isPresent()) {
            AppUserEntity appUserEntity = optionalAppUser.get();
            return new ResponseEntity<>(entityMapper.mapAppUserToDto(appUserEntity), HttpStatus.OK);
        }

        throw new UserNotFoundException(String.format("Нет пользователя с логином %s.", login));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECTOR')")
    @GetMapping(value = "/all/users")
    public ResponseEntity<List<AppUserDtoResponse>> getAllUsers() {
        List<AppUserEntity> appUserEntityList = appUserRepository.findAll();
        return new ResponseEntity<>(entityMapper.mapAppUserToDtoList(appUserEntityList), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @GetMapping(value = "/groups/byDepartment")
    public ResponseEntity<List<GroupDtoResponse>> getAllGroupsByDepartmentId(
            @RequestParam(name = "departmentId") Long departmentId
    ) throws InvalidCredentialsException {
        List<GroupEntity> groupEntities = viewService.getGroupsByDepartmentId(departmentId);
        return new ResponseEntity<>(entityMapper.mapGroupToDtoList(groupEntities), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @GetMapping(value = "/students/byGroup")
    public ResponseEntity<List<StudentDtoResponse>> getStudentsByGroupId(
            @RequestParam(name = "groupId") Long groupId) throws InvalidCredentialsException {
        List<StudentEntity> studentEntities = viewService.getStudentsByGroupId(groupId);
        return new ResponseEntity<>(entityMapper.mapStudentsToDtoResponses(studentEntities), HttpStatus.OK);
    }

}
