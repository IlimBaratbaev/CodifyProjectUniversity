package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.response.SubjectDtoResponse;
import com.example.univercityv1.entity.EmployeeEntity;
import com.example.univercityv1.entity.SpecialityEntity;
import com.example.univercityv1.entity.SubjectEntity;
import com.example.univercityv1.entity.TeacherEntity;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.MapstructMapper;
import com.example.univercityv1.repository.EmployeeRepository;
import com.example.univercityv1.repository.SpecialityRepository;
import com.example.univercityv1.repository.SubjectRepository;
import com.example.univercityv1.repository.TeacherRepository;
import com.example.univercityv1.service.DepartmentHeadService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DepartmentHeadServiceImpl implements DepartmentHeadService {
    private final EmployeeRepository employeeRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final SpecialityRepository specialityRepository;
    private final MapstructMapper mapstructMapper;


    public DepartmentHeadServiceImpl(EmployeeRepository employeeRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, ExceptionCheckingUtil exceptionCheckingUtil, SpecialityRepository specialityRepository, MapstructMapper mapstructMapper) {
        this.employeeRepository = employeeRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.specialityRepository = specialityRepository;
        this.mapstructMapper = mapstructMapper;
    }

    @Override
    public TeacherEntity createNewTeacher(Long employeeId) throws UserNotFoundException {
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findById(employeeId);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, employeeId.toString());
        EmployeeEntity employeeEntity = optionalEmployeeEntity.get();
        TeacherEntity teacherEntity = new TeacherEntity();
        teacherEntity.setEmployeeEntity(employeeEntity);
        teacherRepository.save(teacherEntity);
        return teacherEntity;
    }

    @Override
    public TeacherEntity addSubjectToTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException {
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findById(teacherId);
        exceptionCheckingUtil.checkForPresentOptional(optionalTeacherEntity, "учитель", "не найден!");
        exceptionCheckingUtil.checkForEmptiness(subjectName);
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(subjectName);
        exceptionCheckingUtil.checkForPresentOptional(optionalSubjectEntity, "Предмет", "не найден!");
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        SubjectEntity subjectEntity = optionalSubjectEntity.get();

        if (!teacherEntity.getSubjectEntities().contains(subjectEntity)) {
            teacherEntity.getSubjectEntities().add(subjectEntity);
        }
        teacherRepository.save(teacherEntity);
        if (!subjectEntity.getTeacherEntities().contains(teacherEntity)) {
            subjectEntity.getTeacherEntities().add(teacherEntity);
        }
        subjectRepository.save(subjectEntity);
        return teacherEntity;
    }

    @Override
    public TeacherEntity deleteSubjectFromTeacher(Long teacherId, String subjectName) throws InvalidCredentialsException, SubjectException {
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findById(teacherId);
        exceptionCheckingUtil.checkForPresentOptional(optionalTeacherEntity, "учитель", "не найден!");
        exceptionCheckingUtil.checkForEmptiness(subjectName);
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(subjectName);
        exceptionCheckingUtil.checkForPresentOptional(optionalSubjectEntity, "Предмет", "не найден!");
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        SubjectEntity subjectEntity = optionalSubjectEntity.get();
        if (!teacherEntity.getSubjectEntities().contains(subjectEntity)) {
            throw new SubjectException(String.format("Невозможно удалить предмет %s.", subjectEntity.getTitle()));
        }
        teacherEntity.getSubjectEntities().remove(subjectEntity);
        teacherRepository.save(teacherEntity);
        subjectEntity.getTeacherEntities().remove(teacherEntity);
        subjectRepository.save(subjectEntity);
        return teacherEntity;
    }

    @Override
    public SpecialityEntity addSubjectToSpeciality(Long specialityId, String subjectName) throws InvalidCredentialsException {
        Optional<SpecialityEntity> optionalSpecialityEntity = specialityRepository.findById(specialityId);
        exceptionCheckingUtil.checkForPresentOptional(optionalSpecialityEntity, "Специальность", "не найдена!");
        exceptionCheckingUtil.checkForEmptiness(subjectName);
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(subjectName);
        exceptionCheckingUtil.checkForPresentOptional(optionalSubjectEntity, "Предмет", "не найден!");
        SpecialityEntity specialityEntity = optionalSpecialityEntity.get();
        SubjectEntity subjectEntity = optionalSubjectEntity.get();
        if (!specialityEntity.getSubjectEntities().contains(subjectEntity)) {
            specialityEntity.getSubjectEntities().add(subjectEntity);
            specialityRepository.save(specialityEntity);
            subjectEntity.getSpecialityEntities().add(specialityEntity);
            subjectRepository.save(subjectEntity);
            return specialityEntity;
        }
        return specialityEntity;
    }

    @Override
    public SpecialityEntity getSpeciality(Long specialityId) throws InvalidCredentialsException {
        Optional<SpecialityEntity> optionalSpecialityEntity = specialityRepository.findById(specialityId);
        exceptionCheckingUtil.checkForPresentOptional(optionalSpecialityEntity, "Специальность", "не найдена!");
        return optionalSpecialityEntity.get();
    }
}
