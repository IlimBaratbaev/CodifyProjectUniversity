package com.example.univercityv1.controller;

import com.example.univercityv1.dto.response.AppUserDtoResponse;
import com.example.univercityv1.dto.response.DepartmentDtoResponse;
import com.example.univercityv1.dto.response.FacultyDtoResponse;
import com.example.univercityv1.dto.response.GroupDtoResponse;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.service.impl.ViewServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<FacultyDtoResponse> getAllFaculties() {
        return entityMapper.mapFacultyToDtoList(viewService.getAllFaculties());
    }

    @GetMapping(value = "/all/departments")
    public List<DepartmentDtoResponse> getAllDepartments() {
        return entityMapper.mapDepartmentToDtoList(viewService.getAllDepartments());
    }

    @GetMapping(value = "/all/groups")
    public List<GroupDtoResponse> getAllGroups() {
        return entityMapper.mapGroupToDtoList(viewService.getAllGroups());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECTOR')")
    @GetMapping(value = "/all/user/roles")
    public AppUserDtoResponse getUserInformation(
            @RequestParam String login
    ) throws UserNotFoundException {
        Optional<AppUserEntity> optionalAppUser = appUserRepository.findByLogin(login);
        if (optionalAppUser.isPresent()) {
            AppUserEntity appUserEntity = optionalAppUser.get();
            return entityMapper.mapAppUserToDto(appUserEntity);
        }

        throw new UserNotFoundException(String.format("Нет пользователя с логином %s.", login));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'RECTOR')")
    @GetMapping(value = "/all/users")
    public List<AppUserDtoResponse> getAllUsers() {
        List<AppUserEntity> appUserEntityList = appUserRepository.findAll();
        return entityMapper.mapAppUserToDtoList(appUserEntityList);
    }


}
