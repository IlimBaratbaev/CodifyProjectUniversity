package com.example.univercityv1.controller;

import com.example.univercityv1.dto.response.SpecialityDtoResponse;
import com.example.univercityv1.entity.SpecialityEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.mapper.SpecialtyMapper;
import com.example.univercityv1.service.DepartmentHeadService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SpecialityDtoResponse addSubjectToSpeciality(
            @RequestParam(name = "specialityId") Long specialityId,
            @RequestParam(name = "subjectName") String subjectName
    ) throws InvalidCredentialsException {
        SpecialityEntity specialityEntity = departmentHeadService.addSubjectToSpeciality(specialityId, subjectName);
        return specialtyMapper.mapSpecialityToDtoResponse(specialityEntity);
    }
    @PreAuthorize("hasAnyRole('DEPARTMENT_HEAD')")
    @GetMapping(value = "/get/speciality")
    public SpecialityDtoResponse getSubjectsBySpecialityId(
            @RequestParam(name = "specialityId") Long specialityId
    ) throws InvalidCredentialsException {
        SpecialityEntity specialityEntity = departmentHeadService.getSpeciality(specialityId);
        return specialtyMapper.mapSpecialityToDtoResponse(specialityEntity);
    }
}
