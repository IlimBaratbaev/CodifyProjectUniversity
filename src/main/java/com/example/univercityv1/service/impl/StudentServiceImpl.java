package com.example.univercityv1.service.impl;

import com.example.univercityv1.dto.request.ApplicationFormRequest;
import com.example.univercityv1.dto.request.FinishedAssignmentDtoRequest;
import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.*;
import com.example.univercityv1.repository.*;
import com.example.univercityv1.service.StudentService;
import com.example.univercityv1.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final ApplicationFormRepository applicationFormRepository;
    private final FacultyRepository facultyRepository;
    private final DepartmentRepository departmentRepository;
    private final SpecialityRepository specialityRepository;
    private final AppUserRepository appUserRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final StudentRepository studentRepository;
    private final ScheduleRepository scheduleRepository;
    private final AssignmentRepository assignmentRepository;
    private final SubjectRepository subjectRepository;
    private final FinishedAssignmentRepository finishedAssignmentRepository;

    @Autowired
    public StudentServiceImpl(ApplicationFormRepository applicationFormRepository, FacultyRepository facultyRepository, DepartmentRepository departmentRepository, SpecialityRepository specialityRepository, AppUserRepository appUserRepository, ExceptionCheckingUtil exceptionCheckingUtil, StudentRepository studentRepository, ScheduleRepository scheduleRepository, AssignmentRepository assignmentRepository, SubjectRepository subjectRepository, FinishedAssignmentRepository finishedAssignmentRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.facultyRepository = facultyRepository;
        this.departmentRepository = departmentRepository;
        this.specialityRepository = specialityRepository;
        this.appUserRepository = appUserRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.studentRepository = studentRepository;
        this.scheduleRepository = scheduleRepository;
        this.assignmentRepository = assignmentRepository;
        this.subjectRepository = subjectRepository;
        this.finishedAssignmentRepository = finishedAssignmentRepository;
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

    @Override
    public List<ScheduleEntity> getMyStudentSchedule() throws UserNotFoundException {
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, appUserEntity.getLogin());
        StudentEntity studentEntity = optionalStudentEntity.get();
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findAllByLessonEntity_GroupEntity_Id(studentEntity.getGroupEntity().getId());
        return scheduleEntities;
    }

    @Override
    public List<Assignment> getMyAssignmentsBySubjectName(String subjectName) throws InvalidCredentialsException, SubjectException, UserNotFoundException {
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, appUserEntity.getLogin());
        StudentEntity studentEntity = optionalStudentEntity.get();
        exceptionCheckingUtil.checkForEmptiness(subjectName);
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(subjectName);
        exceptionCheckingUtil.checkForPresentSubject(optionalSubjectEntity);
        List<Assignment> assignments = assignmentRepository
                .findAllByLessonEntity_SubjectEntityAndLessonEntity_GroupEntity(optionalSubjectEntity.get(), studentEntity.getGroupEntity());
        return assignments;
    }

    @Override
    public FinishedAssignment sendAssignment(FinishedAssignmentDtoRequest finishedAssignmentDtoRequest) throws UserNotFoundException, AssignmentException {
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, appUserEntity.getLogin());
        StudentEntity studentEntity = optionalStudentEntity.get();
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(finishedAssignmentDtoRequest.getAssignmentId());
        exceptionCheckingUtil.checkForPresentAssignment(optionalAssignment);
        if (!studentEntity.getGroupEntity().equals(optionalAssignment.get().getGroupEntity())) {
            throw new AssignmentException("Задание было отправлено в другую группу!");
        }
        FinishedAssignment finishedAssignment = new FinishedAssignment();
        finishedAssignment
                .setAssignment(optionalAssignment.get())
                .setStudentEntity(studentEntity)
                .setSolution(finishedAssignmentDtoRequest.getSolution())
                .setSentTime(LocalDateTime.now());
        finishedAssignmentRepository.save(finishedAssignment);
        return finishedAssignment;
    }
}
