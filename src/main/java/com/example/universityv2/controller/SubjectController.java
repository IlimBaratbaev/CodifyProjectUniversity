package com.example.universityv2.controller;

import com.example.universityv2.dto.response.SpecialityDtoResponse;
import com.example.universityv2.entity.SpecialityEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.mapper.SpecialtyMapper;
import com.example.universityv2.service.DepartmentHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    private final DepartmentHeadService departmentHeadService;
    private final SpecialtyMapper specialtyMapper;

    @Autowired
    public SubjectController(DepartmentHeadService departmentHeadService, SpecialtyMapper specialtyMapper) {
        this.departmentHeadService = departmentHeadService;
        this.specialtyMapper = specialtyMapper;
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @PostMapping(value = "/add/speciality")
    public ResponseEntity<SpecialityDtoResponse> addSubjectToSpeciality(
            @RequestParam(name = "specialityId") Long specialityId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException {
        SpecialityEntity specialityEntity = departmentHeadService.addSubjectToSpeciality(specialityId, subjectName);
        return new ResponseEntity<>(specialtyMapper.mapSpecialityToDtoResponse(specialityEntity), HttpStatus.OK);
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @GetMapping(value = "/get/speciality")
    public ResponseEntity<SpecialityDtoResponse> getSubjectsBySpecialityId(
            @RequestParam(name = "specialityId") Long specialityId
    ) throws InvalidCredentialsException {
        SpecialityEntity specialityEntity = departmentHeadService.getSpeciality(specialityId);
        return new ResponseEntity<>(specialtyMapper.mapSpecialityToDtoResponse(specialityEntity), HttpStatus.OK);
    }
}
