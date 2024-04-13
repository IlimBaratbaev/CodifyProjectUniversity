package com.example.universityv2.controller;

import com.example.universityv2.dto.response.AppUserDtoResponse;
import com.example.universityv2.dto.response.EmployeeDtoResponse;
import com.example.universityv2.entity.AppUserEntity;
import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.exception.EmployeeException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.RoleException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.mapper.EntityMapper;
import com.example.universityv2.repository.AppRoleRepository;
import com.example.universityv2.repository.AppUserRepository;
import com.example.universityv2.service.CreateEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<EmployeeDtoResponse> addEmployee(@RequestParam String login,
                                                           @AuthenticationPrincipal UserDetails userDetails
    ) throws UserNotFoundException, EmployeeException {
        String username = userDetails.getUsername();
        return new ResponseEntity<>(entityMapper.mapEmployeeToDto(createEditService.addNewEmployee(login, username)), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'ADMIN')")
    @PostMapping(value = "/user/role/give")
    public ResponseEntity<AppUserDtoResponse> giveRoleByLoginAndStringRole(
            @RequestParam(value = "login") String login,
            @RequestParam(value = "role") String role) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        AppUserEntity appUserEntity = createEditService.giveRoleToUser(login, role);
        return new ResponseEntity<>(entityMapper.mapAppUserToDto(appUserEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/employee/delete")
    public ResponseEntity<EmployeeDtoResponse> deleteEmployee(@RequestParam String login,
                                              @AuthenticationPrincipal UserDetails userDetails)
            throws UserNotFoundException, EmployeeException {
        String username = userDetails.getUsername();
        EmployeeEntity employeeEntity = createEditService.deleteEmployee(login, username);
        return new ResponseEntity<>(entityMapper.mapEmployeeToDto(employeeEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'DEAN')")
    @PostMapping(value = "/employee/setPosition")
    public ResponseEntity<EmployeeDtoResponse> editPosition(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "position") String position
    ) throws InvalidCredentialsException, UserNotFoundException {
        EmployeeEntity employeeEntity = createEditService.editEmployeePosition(login, position);
        return new ResponseEntity<>(entityMapper.mapEmployeeToDto(employeeEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR')")
    @PostMapping(value = "/employee/setDepartment")
    public ResponseEntity<EmployeeDtoResponse> setDepartment(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "departmentId") Long departmentId
    ) throws  UserNotFoundException {
        EmployeeEntity employeeEntity = createEditService.setDepartmentToEmployee(login, departmentId);
        return new ResponseEntity<>(entityMapper.mapEmployeeToDto(employeeEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR', 'ADMIN')")
    @DeleteMapping(value = "/user/role/delete")
    public ResponseEntity<AppUserDtoResponse> editUserRoles(@RequestParam(value = "login") String login,
                                            @RequestParam(value = "roleToDelete", required = false) String roleToDelete
    ) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        AppUserEntity appUserEntity = createEditService.deleteUserRole(login, roleToDelete);
        return new ResponseEntity<>(entityMapper.mapAppUserToDto(appUserEntity), HttpStatus.OK);
    }
}
