package com.example.universityv2.controller;

import com.example.universityv2.dto.request.AppUserDtoRequest;
import com.example.universityv2.dto.request.ApplicationFormRequest;
import com.example.universityv2.dto.response.AppUserDtoResponse;
import com.example.universityv2.dto.response.ApplicationFormResponse;
import com.example.universityv2.dto.response.StudentDtoResponse;
import com.example.universityv2.entity.ApplicationFormEntity;
import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.*;
import com.example.universityv2.mapper.ApplicationFormMapper;
import com.example.universityv2.mapper.EntityMapper;
import com.example.universityv2.repository.ApplicationFormRepository;
import com.example.universityv2.service.AppUserService;
import com.example.universityv2.service.StudentEnrollmentService;
import com.example.universityv2.service.RectorService;
import com.example.universityv2.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AppUserDtoResponse> registerNewUser(
            @RequestBody AppUserDtoRequest appUserDtoRequest
    ) throws InvalidCredentialsException, UserException {
        return new ResponseEntity<>(entityMapper.mapAppUserToDto(appUserService.registerNewUser(appUserDtoRequest)), HttpStatus.CREATED);
    }

    @PostMapping(value = "/application")
    public ResponseEntity<ApplicationFormResponse> registerToUniversity(
            @RequestBody ApplicationFormRequest applicationFormRequest,
            @AuthenticationPrincipal UserDetails userDetails
            ) throws InvalidCredentialsException, ApplicationFormException {
        String login = userDetails.getUsername();
        ApplicationFormEntity applicationFormEntity = studentService.registerToUniversity(applicationFormRequest, login);
        return new ResponseEntity<>(applicationFormMapper.mapApplicationFormToDto(applicationFormEntity), HttpStatus.CREATED);
    }


    @PreAuthorize("hasAnyRole('RECTOR')")
    @PostMapping(value = "/admission")
    public ResponseEntity<ApplicationFormResponse> setAdmissionDecision(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "decision") Boolean decision,
            @RequestParam(value = "login") String secretaryLogin,
            @AuthenticationPrincipal UserDetails userDetails
    ) throws InvalidCredentialsException, UserNotFoundException, ApplicationFormException {
        String fromWhom = userDetails.getUsername();
        ApplicationFormEntity applicationFormEntity = rectorService.admissionDecision(id, decision, secretaryLogin, fromWhom);
        return new ResponseEntity<>(applicationFormMapper.mapApplicationFormToDto(applicationFormEntity), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('RECTOR_SEC')")
    @PostMapping(value = "/student/faculty")
    public ResponseEntity<StudentEntity> registerToFaculty(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException {
        return new ResponseEntity<>(studentEnrollmentService.enrollStudentToFaculty(studentLogin, id), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('DEAN_SEC')")
    @PostMapping(value = "/student/department")
    public ResponseEntity<StudentEntity> registerToDepartment(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException {
        return new ResponseEntity<>(studentEnrollmentService.enrollStudentToDepartment(studentLogin, id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD', 'RECTOR_SEC')")
    @PostMapping(value = "/student/speciality")
    public ResponseEntity<StudentDtoResponse> registerToSpeciality(
            @RequestParam(value = "id") Long id,
            @RequestParam(value = "login") String studentLogin
    ) throws UserNotFoundException, InvalidCredentialsException, RoleException {
        return new ResponseEntity<>(entityMapper.mapStudentToDtoResponse(studentEnrollmentService.enrollStudentToSpeciality(studentLogin, id)), HttpStatus.OK);
    }
}
