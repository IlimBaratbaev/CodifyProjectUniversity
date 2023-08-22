package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.ApplicationFormRequest;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.ApplicationFormException;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.repository.*;
import com.example.univercityv1.service.StudentService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final ApplicationFormRepository applicationFormRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecialityRepository specialityRepository;
    private final AppUserRepository appUserRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;

    @Autowired
    public StudentServiceImpl(ApplicationFormRepository applicationFormRepository, FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SpecialityRepository specialityRepository, AppUserRepository appUserRepository, ExceptionCheckingUtil exceptionCheckingUtil) {
        this.applicationFormRepository = applicationFormRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.specialityRepository = specialityRepository;
        this.appUserRepository = appUserRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
    }


    @Override
    public ApplicationFormEntity registerToUniversity(ApplicationFormRequest applicationFormRequest, String login) throws InvalidCredentialsException, ApplicationFormException {
        AppUserEntity appUserEntity = appUserRepository.findByLogin(login).get();

        Optional<FacultyEntity> optionalFacultyEntity = facultyRepository.findById(applicationFormRequest.getFacultyId());
        exceptionCheckingUtil.checkForPresentOptional(optionalFacultyEntity, "Факультет", "не найден");
        FacultyEntity facultyEntity = optionalFacultyEntity.get();

        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(applicationFormRequest.getFacultyId());
        DepartmentEntity departmentEntity = optionalDepartmentEntity.get();
        exceptionCheckingUtil.checkForPresentOptional(optionalDepartmentEntity, "Кафедра", "не найдена");

        if (!departmentEntity.getFacultyEntity().equals(facultyEntity)) {
            throw new ApplicationFormException(String.format("Кафедра %s не является частью факультета %s.", departmentEntity.getTitle(), facultyEntity.getTitle()));
        }

        Optional<SpecialityEntity> optionalSpecialityEntity = specialityRepository.findById(applicationFormRequest.getFacultyId());
        exceptionCheckingUtil.checkForPresentOptional(optionalSpecialityEntity, "Специальность", "не найдена");
        SpecialityEntity specialityEntity = optionalSpecialityEntity.get();

        if (!specialityEntity.getDepartmentEntity().equals(departmentEntity)) {
            throw new ApplicationFormException(String.format("Специальность %s не является частью кафедры %s.", specialityEntity.getSpecialityTitle(), departmentEntity.getTitle()));
        }

        ApplicationFormEntity result = new ApplicationFormEntity();
        result
                .setContentText(applicationFormRequest.getContentText())
                .setAppUserEntity(appUserEntity)
                .setFacultyEntity(facultyEntity)
                .setDepartmentEntity(departmentEntity)
                .setSpecialityEntity(specialityEntity);
        applicationFormRepository.save(result);
        return result;
    }
}
