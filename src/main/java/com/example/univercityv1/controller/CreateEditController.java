package com.example.univercityv1.controller;

import com.example.univercityv1.dto.response.AppUserDtoResponse;
import com.example.univercityv1.dto.response.EmployeeDtoResponse;
import com.example.univercityv1.entity.AppUserEntity;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.exception.EmployeeException;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.RoleException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.repository.AppRoleRepository;
import com.example.univercityv1.repository.AppUserRepository;
import com.example.univercityv1.service.CreateEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/create")
public class CreateEditController {
    private final CreateEditService createEditService;
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final EntityMapper entityMapper;

    @Autowired
    public CreateEditController(CreateEditService createEditService, AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, EntityMapper entityMapper) {
        this.createEditService = createEditService;
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.entityMapper = entityMapper;
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/add/employee")
    public EmployeeDtoResponse addEmployee(@RequestParam String login,
                                           @AuthenticationPrincipal UserDetails userDetails
    ) throws UserNotFoundException, EmployeeException {
        String username = userDetails.getUsername();
        return entityMapper.mapEmployeeToDto(createEditService.addNewEmployee(login, username));
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'ADMIN')")
    @PostMapping(value = "/user/role/give")
    public AppUserDtoResponse giveRoleByLoginAndStringRole(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "role") String role) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        AppUserEntity appUserEntity = createEditService.giveRoleToUser(login, role);
        return entityMapper.mapAppUserToDto(appUserEntity);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/employee/delete")
    public EmployeeDtoResponse deleteEmployee(@RequestParam String login,
                                              @AuthenticationPrincipal UserDetails userDetails)
            throws UserNotFoundException, EmployeeException {
        String username = userDetails.getUsername();
        EmployeeEntity employeeEntity = createEditService.deleteEmployee(login, username);
        return entityMapper.mapEmployeeToDto(employeeEntity);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/employee/setPosition")
    public EmployeeDtoResponse editPosition(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "position") String position
    ) throws InvalidCredentialsException, UserNotFoundException {
        EmployeeEntity employeeEntity = createEditService.editEmployeePosition(login, position);
        return entityMapper.mapEmployeeToDto(employeeEntity);
    }

    @PreAuthorize("hasAnyRole('RECTOR')")
    @PostMapping(value = "/employee/setDepartment")
    public EmployeeDtoResponse setDepartment(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "departmentId") Long departmentId
    ) throws  UserNotFoundException {
        EmployeeEntity employeeEntity = createEditService.setDepartmentToEmployee(login, departmentId);
        return entityMapper.mapEmployeeToDto(employeeEntity);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'ADMIN')")
    @DeleteMapping(value = "/user/role/delete")
    public AppUserDtoResponse editUserRoles(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "roleToDelete", required = false) String roleToDelete
    ) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        AppUserEntity appUserEntity = createEditService.deleteUserRole(login, roleToDelete);
        return entityMapper.mapAppUserToDto(appUserEntity);
    }
}
