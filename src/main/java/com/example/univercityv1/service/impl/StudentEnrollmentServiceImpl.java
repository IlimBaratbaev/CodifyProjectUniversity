package com.example.univercityv1.service.impl;

import com.example.univercityv1.entity.DepartmentEntity;
import com.example.univercityv1.entity.FacultyEntity;
import com.example.univercityv1.entity.SpecialityEntity;
import com.example.univercityv1.entity.StudentEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.repository.*;
import com.example.univercityv1.service.StudentEnrollmentService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
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

    public StudentEnrollmentServiceImpl(StudentRepository studentRepository, ExceptionCheckingUtil exceptionCheckingUtil, FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SpecialityRepository specialityRepository, GroupRepository groupRepository) {
        this.studentRepository = studentRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.specialityRepository = specialityRepository;
        this.groupRepository = groupRepository;
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
    public StudentEntity enrollStudentToSpeciality(String login, Long id) throws InvalidCredentialsException, UserNotFoundException {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity_Login(login);
        exceptionCheckingUtil.checkForEmptiness(login);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, login);
        StudentEntity studentEntity = optionalStudentEntity.get();
        Optional<SpecialityEntity> optionalSpecialityEntity = specialityRepository.findById(id);
        exceptionCheckingUtil.checkForPresentOptional(optionalSpecialityEntity, id.toString(), "нет специальности с таким id.");
        studentEntity.setSpecialityEntity(optionalSpecialityEntity.get());
        studentEntity.setGroupEntity(groupRepository.findBySpecialityEntityId(id));
        studentRepository.save(studentEntity);
        return null;
    }
}
