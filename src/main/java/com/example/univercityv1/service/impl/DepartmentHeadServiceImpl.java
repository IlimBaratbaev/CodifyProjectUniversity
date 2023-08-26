package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.LessonDtoRequest;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.LessonException;
import com.example.univercityv1.exception.SubjectException;
import com.example.univercityv1.exception.UserNotFoundException;
import com.example.univercityv1.mapper.MapstructMapper;
import com.example.univercityv1.repository.*;
import com.example.univercityv1.service.DepartmentHeadService;
import com.example.univercityv1.status.LessonStatus;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;

@Service
public class DepartmentHeadServiceImpl implements DepartmentHeadService {
    private final EmployeeRepository employeeRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final SpecialityRepository specialityRepository;
    private final MapstructMapper mapstructMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final LessonRepository lessonRepository;
    private final ScheduleRepository scheduleRepository;


    public DepartmentHeadServiceImpl(EmployeeRepository employeeRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository, ExceptionCheckingUtil exceptionCheckingUtil, SpecialityRepository specialityRepository, MapstructMapper mapstructMapper, StudentRepository studentRepository, GroupRepository groupRepository, LessonRepository lessonRepository, ScheduleRepository scheduleRepository) {
        this.employeeRepository = employeeRepository;
        this.teacherRepository = teacherRepository;
        this.subjectRepository = subjectRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.specialityRepository = specialityRepository;
        this.mapstructMapper = mapstructMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.lessonRepository = lessonRepository;
        this.scheduleRepository = scheduleRepository;
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

    @Override
    public StudentEntity studentExpulsion(Long studentId) throws UserNotFoundException {
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findById(studentId);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, studentId.toString());
        StudentEntity studentEntity = optionalStudentEntity.get();
        studentEntity.setDeleted(true);
        studentRepository.save(studentEntity);
        return studentEntity;
    }

    @Override
    public LessonEntity createNewLesson(LessonDtoRequest lessonDtoRequest) throws LessonException, InvalidCredentialsException {
        if (Objects.isNull(lessonDtoRequest)) {
            throw new LessonException("Запрос на создание нового урока не должен быть пустым!");
        }
        if (Objects.isNull(lessonDtoRequest.getGroupId()) || lessonDtoRequest.getSubjectName().isBlank() || Objects.isNull(lessonDtoRequest.getTeacherId())) {
            throw new InvalidCredentialsException("Значения не должны быть пустыми");
        }
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findById(lessonDtoRequest.getTeacherId());
        exceptionCheckingUtil.checkForPresentOptional(optionalTeacherEntity, "Учитель", "не найден!");
        Optional<GroupEntity> optionalGroupEntity = groupRepository.findById(lessonDtoRequest.getGroupId());
        exceptionCheckingUtil.checkForPresentOptional(optionalGroupEntity, "Группа", "не найдена!");
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(lessonDtoRequest.getSubjectName());
        exceptionCheckingUtil.checkForPresentOptional(optionalSubjectEntity, "Предмет", "не найден!");
        String dateString = lessonDtoRequest.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime lessonDateTime;
        try {
            lessonDateTime = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Строка даты не соответствует формату ISO-8601", ex.getParsedString(), ex.getErrorIndex());
        }
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        SubjectEntity subjectEntity = optionalSubjectEntity.get();
        GroupEntity groupEntity = optionalGroupEntity.get();
        LessonEntity lessonEntity = new LessonEntity();
        lessonEntity
                .setGroupEntity(groupEntity)
                .setLessonStatus(LessonStatus.NOT_STARTED.toString())
                .setLocalDateTime(lessonDateTime)
                .setTeacherEntity(teacherEntity)
                .setSubjectEntity(subjectEntity);
        lessonRepository.save(lessonEntity);
        ScheduleEntity scheduleEntity = new ScheduleEntity();
        scheduleEntity.setLessonEntity(lessonEntity);
        scheduleRepository.save(scheduleEntity);
        return lessonEntity;
    }
}
