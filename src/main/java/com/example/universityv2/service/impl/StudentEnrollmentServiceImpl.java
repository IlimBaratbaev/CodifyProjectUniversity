package com.example.universityv2.service.impl;

import com.example.universityv2.entity.DepartmentEntity;
import com.example.universityv2.entity.FacultyEntity;
import com.example.universityv2.entity.SpecialityEntity;
import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.RoleException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.repository.*;
import com.example.universityv2.service.StudentEnrollmentService;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService {
    private final StudentRepository studentRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecialityRepository specialityRepository;
    private final GroupRepository groupRepository;
    private final CreateEditServiceImpl createEditService;

    public StudentEnrollmentServiceImpl(StudentRepository studentRepository, ExceptionCheckingUtil exceptionCheckingUtil, FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SpecialityRepository specialityRepository, GroupRepository groupRepository, CreateEditServiceImpl createEditService) {
        this.studentRepository = studentRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.specialityRepository = specialityRepository;
        this.groupRepository = groupRepository;
        this.createEditService = createEditService;
    }

    @Override
    public StudentEntity enrollStudentToFaculty(String login, Long id) throws InvalidCredentialsException, UserNotFoundException {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForEmptiness(login);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, login);
        StudentEntity studentEntity = optionalStudentEntity.get();
        Optional<FacultyEntity> optionalFacultyEntity = facultyRepository.findById(id);
        exceptionCheckingUtil.checkForPresentOptional(optionalFacultyEntity, id.toString(), "нет факультета с таким id.");
        studentEntity.setFacultyEntity(optionalFacultyEntity.get());
        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    public StudentEntity enrollStudentToDepartment(String login, Long id) throws InvalidCredentialsException, UserNotFoundException {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForEmptiness(login);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, login);
        StudentEntity studentEntity = optionalStudentEntity.get();
        Optional<DepartmentEntity> optionalDepartmentEntity = departmentRepository.findById(id);
        exceptionCheckingUtil.checkForPresentOptional(optionalDepartmentEntity, id.toString(), "нет кафедры с таким id.");
        studentEntity.setDepartmentEntity(optionalDepartmentEntity.get());
        return studentEntity;
    }

    @Override
    public StudentEntity enrollStudentToSpeciality(String login, Long id) throws InvalidCredentialsException, UserNotFoundException, RoleException {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForEmptiness(login);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, login);
        StudentEntity studentEntity = optionalStudentEntity.get();
        Optional<SpecialityEntity> optionalSpecialityEntity = specialityRepository.findById(id);
        exceptionCheckingUtil.checkForPresentOptional(optionalSpecialityEntity, id.toString(), "нет специальности с таким id.");
        studentEntity.setSpecialityEntity(optionalSpecialityEntity.get());
        studentEntity.setGroupEntity(groupRepository.findBySpecialityEntityId(id));
        this.createEditService.giveRoleToUser(login, "STUDENT");
        studentRepository.save(studentEntity);
        return studentEntity;
    }
}
