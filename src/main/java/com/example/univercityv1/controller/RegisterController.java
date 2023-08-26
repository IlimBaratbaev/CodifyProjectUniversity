package com.example.univercityv1.controller;

import com.example.univercityv1.dto.request.AppUserDtoRequest;
import com.example.univercityv1.dto.request.ApplicationFormRequest;
import com.example.univercityv1.dto.response.AppUserDtoResponse;
import com.example.univercityv1.dto.response.ApplicationFormResponse;
import com.example.univercityv1.dto.response.StudentDtoResponse;
import com.example.univercityv1.entity.ApplicationFormEntity;
import com.example.univercityv1.entity.StudentEntity;
import com.example.univercityv1.exception.*;
import com.example.univercityv1.mapper.ApplicationFormMapper;
import com.example.univercityv1.mapper.EntityMapper;
import com.example.univercityv1.repository.ApplicationFormRepository;
import com.example.univercityv1.service.AppUserService;
import com.example.univercityv1.service.StudentEnrollmentService;
import com.example.univercityv1.service.RectorService;
import com.example.univercityv1.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/register")
public class RegisterController {

    private final AppUserService appUserService;
    private final StudentService studentService;
    private final EntityMapper entityMapper;
    private final ApplicationFormMapper applicationFormMapper;
    private final ApplicationFormRepository applicationFormRepository;
    private final RectorService rectorService;

    private final StudentEnrollmentService studentEnrollmentService;

    @Autowired
    public RegisterController(AppUserService appUserService, StudentService studentService, EntityMapper entityMapper, ApplicationFormMapper applicationFormMapper, ApplicationFormRepository applicationFormRepository, RectorService rectorService, StudentEnrollmentService studentEnrollmentService) {
        this.appUserService = appUserService;
        this.studentService = studentService;
        this.entityMapper = entityMapper;
        this.applicationFormMapper = applicationFormMapper;
        this.applicationFormRepository = applicationFormRepository;
        this.rectorService = rectorService;
        this.studentEnrollmentService = studentEnrollmentService;
    }

    @PostMapping(value = "/user")
    public AppUserDtoResponse registerNewUser(
            @RequestBody AppUserDtoRequest appUserDtoRequest
    ) throws InvalidCredentialsException, UserException {
        return entityMapper.mapAppUserToDto(appUserService.registerNewUser(appUserDtoRequest));
    }

    @PostMapping(value = "/application")
    public ApplicationFormResponse registerToUniversity(
            @RequestBody ApplicationFormRequest applicationFormRequest,
            @AuthenticationPrincipal UserDetails userDetails
            ) throws InvalidCredentialsException, ApplicationFormException {
        String login = userDetails.getUsername();
        ApplicationFormEntity applicationFormEntity = studentService.registerToUniversity(applicationFormRequest, login);
        return applicationFormMapper.mapApplicationFormToDto(applicationFormEntity);
    }


    @PreAuthorize("hasAnyRole('RECTOR')")
    @PostMapping(value = "/admission")
    public ApplicationFormResponse setAdmissionDecision(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "decision") Boolean decision,
            @RequestParam(value = "login") String secretaryLogin,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws InvalidCredentialsException, UserNotFoundException, ApplicationFormException {
        String fromWhom = userDetails.getUsername();
        ApplicationFormEntity applicationFormEntity = rectorService.admissionDecision(id, decision, secretaryLogin, fromWhom);
        return applicationFormMapper.mapApplicationFormToDto(applicationFormEntity);
    }

    @PreAuthorize("hasAnyRole('RECTOR_SEC')")
    @PostMapping(value = "/student/faculty")
    public StudentEntity registerToFaculty(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException {
        return studentEnrollmentService.enrollStudentToFaculty(studentLogin, id);
    }
    @PreAuthorize("hasAnyRole('DEAN_SEC')")
    @PostMapping(value = "/student/department")
    public StudentEntity registerToDepartment(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException {
        return studentEnrollmentService.enrollStudentToDepartment(studentLogin, id);
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD', 'RECTOR_SEC')")
    @PostMapping(value = "/student/speciality")
    public StudentDtoResponse registerToSpeciality(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        return entityMapper.mapStudentToDtoResponse(studentEnrollmentService.enrollStudentToSpeciality(studentLogin, id));
    }
}
