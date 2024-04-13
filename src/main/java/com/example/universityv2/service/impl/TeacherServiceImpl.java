package com.example.universityv2.service.impl;

import com.example.universityv2.dto.request.AssignmentDtoRequest;
import com.example.universityv2.dto.request.MarkDtoRequest;
import com.example.universityv2.entity.*;
import com.example.universityv2.exception.*;
import com.example.universityv2.repository.*;
import com.example.universityv2.service.TeacherService;
import com.example.universityv2.status.LessonStatus;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final ScheduleRepository scheduleRepository;
    private final LessonRepository lessonRepository;
    private final StudentRepository studentRepository;
    private final LessonAttendanceRepository lessonAttendanceRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final AssignmentRepository assignmentRepository;
    private final FinishedAssignmentRepository finishedAssignmentRepository;
    private final MarkRepository markRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, ExceptionCheckingUtil exceptionCheckingUtil, ScheduleRepository scheduleRepository, LessonRepository lessonRepository, StudentRepository studentRepository, LessonAttendanceRepository lessonAttendanceRepository, GroupRepository groupRepository, SubjectRepository subjectRepository, AssignmentRepository assignmentRepository, FinishedAssignmentRepository finishedAssignmentRepository, MarkRepository markRepository) {
        this.teacherRepository = teacherRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.scheduleRepository = scheduleRepository;
        this.lessonRepository = lessonRepository;
        this.studentRepository = studentRepository;
        this.lessonAttendanceRepository = lessonAttendanceRepository;
        this.groupRepository = groupRepository;
        this.subjectRepository = subjectRepository;
        this.assignmentRepository = assignmentRepository;
        this.finishedAssignmentRepository = finishedAssignmentRepository;
        this.markRepository = markRepository;
    }

    @Override
    public List<ScheduleEntity> getMySchedule() throws TeacherException {
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findByEmployeeEntity_AppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentTeacher(optionalTeacherEntity);
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        List<ScheduleEntity> scheduleEntities = scheduleRepository.findAllByLessonEntity_TeacherEntity(teacherEntity);
        return scheduleEntities;
    }

    @Override
    public LessonEntity startLesson(Long lessonId) throws TeacherException, LessonException, InvalidCredentialsException {
        Optional<LessonEntity> optionalLessonEntity = lessonRepository.findById(lessonId);
        exceptionCheckingUtil.checkForPresentLesson(optionalLessonEntity);
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findByEmployeeEntity_AppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentTeacher(optionalTeacherEntity);
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        LessonEntity lessonEntity = optionalLessonEntity.get();
        if (!lessonEntity.getTeacherEntity().equals(teacherEntity)) {
            throw new LessonException("Урок проводится другим учителем!");
        }

        Optional<List<StudentEntity>> optionalStudentEntities = studentRepository.findAllByGroupEntityId(lessonEntity.getGroupEntity().getId());
        exceptionCheckingUtil.checkForPresentOptional(optionalStudentEntities, "Студетов", "нет!");
        for (StudentEntity studentEntity : optionalStudentEntities.get()) {
            LessonAttendanceEntity lessonAttendanceEntity = new LessonAttendanceEntity();
            lessonAttendanceEntity
                    .setLessonEntity(lessonEntity)
                    .setStudentEntity(studentEntity)
                    .setPresent(false);
            lessonAttendanceRepository.save(lessonAttendanceEntity);
        }
        lessonEntity.setLessonStatus(LessonStatus.STARTED.toString());
        lessonRepository.save(lessonEntity);
        return lessonEntity;
    }

    @Override
    public LessonEntity endLesson(Long lessonId) throws TeacherException, LessonException, InvalidCredentialsException {
        Optional<LessonEntity> optionalLessonEntity = lessonRepository.findById(lessonId);
        exceptionCheckingUtil.checkForPresentLesson(optionalLessonEntity);
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findByEmployeeEntity_AppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentTeacher(optionalTeacherEntity);
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        LessonEntity lessonEntity = optionalLessonEntity.get();
        if (!lessonEntity.getTeacherEntity().equals(teacherEntity)) {
            throw new LessonException("Урок проводится другим учителем!");
        }
        lessonEntity.setLessonStatus(LessonStatus.FINISHED.toString());
        lessonRepository.save(lessonEntity);
        Optional<ScheduleEntity> optionalScheduleEntity = scheduleRepository.findByLessonEntity(lessonEntity);
        exceptionCheckingUtil.checkForPresentOptional(optionalScheduleEntity, "Расписание", "не найдено!");
        scheduleRepository.delete(optionalScheduleEntity.get());
        return lessonEntity;
    }

    @Override
    public List<LessonAttendanceEntity> everyoneAttended(Long lessonId) throws LessonException, InvalidCredentialsException {
        Optional<LessonEntity> optionalLessonEntity = lessonRepository.findById(lessonId);
        exceptionCheckingUtil.checkForPresentLesson(optionalLessonEntity);
        LessonEntity lessonEntity = optionalLessonEntity.get();
        Optional<List<LessonAttendanceEntity>> optionalLessonAttendanceEntities = lessonAttendanceRepository.findAllByLessonEntityId(lessonId);
        exceptionCheckingUtil.checkForPresentOptional(optionalLessonAttendanceEntities, "Урок", "не начался!");
        List<LessonAttendanceEntity> lessonAttendanceEntities = optionalLessonAttendanceEntities.get();
        for (LessonAttendanceEntity lessonAttendanceEntity : lessonAttendanceEntities) {
            lessonAttendanceEntity.setPresent(true);
            lessonAttendanceRepository.save(lessonAttendanceEntity);
        }
        return lessonAttendanceEntities;
    }

    @Override
    public List<LessonAttendanceEntity> studentNotAttended(Long lessonId, Long studentId) throws InvalidCredentialsException {
        Optional<List<LessonAttendanceEntity>> optionalLessonAttendanceEntities = lessonAttendanceRepository.findAllByLessonEntityId(lessonId);
        exceptionCheckingUtil.checkForPresentOptional(optionalLessonAttendanceEntities, "Урок", "не начался!");
        Optional<LessonAttendanceEntity> optionalLessonAttendanceEntity = lessonAttendanceRepository.findByLessonEntityIdAndStudentEntityId(lessonId, studentId);
        exceptionCheckingUtil.checkForPresentOptional(optionalLessonAttendanceEntity, "Посещение", "имеет ошибку, возможно урок не начался.");
        LessonAttendanceEntity lessonAttendanceEntity = optionalLessonAttendanceEntity.get();
        lessonAttendanceEntity.setPresent(false);
        lessonAttendanceRepository.save(lessonAttendanceEntity);
        return optionalLessonAttendanceEntities.get();
    }

    @Override
    public List<LessonAttendanceEntity> studentAttended(Long lessonId, Long studentId) throws InvalidCredentialsException {
        Optional<List<LessonAttendanceEntity>> optionalLessonAttendanceEntities = lessonAttendanceRepository.findAllByLessonEntityId(lessonId);
        exceptionCheckingUtil.checkForPresentOptional(optionalLessonAttendanceEntities, "Урок", "не начался!");
        Optional<LessonAttendanceEntity> optionalLessonAttendanceEntity = lessonAttendanceRepository.findByLessonEntityIdAndStudentEntityId(lessonId, studentId);
        exceptionCheckingUtil.checkForPresentOptional(optionalLessonAttendanceEntity, "Посещение", "имеет ошибку, возможно урок не начался.");
        LessonAttendanceEntity lessonAttendanceEntity = optionalLessonAttendanceEntity.get();
        lessonAttendanceEntity.setPresent(true);
        lessonAttendanceRepository.save(lessonAttendanceEntity);
        return optionalLessonAttendanceEntities.get();
    }

    @Override
    public Assignment createNewAssignment(AssignmentDtoRequest assignmentDtoRequest) throws TeacherException, InvalidCredentialsException, LessonException {
        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Optional<TeacherEntity> optionalTeacherEntity = teacherRepository.findByEmployeeEntity_AppUserEntity(appUserEntity);
        exceptionCheckingUtil.checkForPresentTeacher(optionalTeacherEntity);
        TeacherEntity teacherEntity = optionalTeacherEntity.get();
        Optional<GroupEntity> optionalGroupEntity = groupRepository.findById(assignmentDtoRequest.getGroupId());
        exceptionCheckingUtil.checkForPresentOptional(optionalGroupEntity, "Группы", "нет!");
        Optional<SubjectEntity> optionalSubjectEntity = subjectRepository.findByTitle(assignmentDtoRequest.getSubjectName());
        exceptionCheckingUtil.checkForPresentOptional(optionalSubjectEntity, "Предмета", assignmentDtoRequest.getSubjectName() + " нет!");
        Optional<LessonEntity> optionalLessonEntity = lessonRepository.findById(assignmentDtoRequest.getLessonId());
        exceptionCheckingUtil.checkForPresentLesson(optionalLessonEntity);
        LessonEntity lessonEntity = optionalLessonEntity.get();
        SubjectEntity subjectEntity = optionalSubjectEntity.get();
        GroupEntity groupEntity = optionalGroupEntity.get();
        String dateString = assignmentDtoRequest.getDeadline();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        LocalDateTime assignmentDeadline;
        try {
            assignmentDeadline = LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Строка даты не соответствует формату ISO-8601", ex.getParsedString(), ex.getErrorIndex());
        }
        if (!(teacherEntity.getSubjectEntities().contains(subjectEntity) && lessonEntity.getTeacherEntity().equals(teacherEntity))) {
            throw new TeacherException("Учитель не может отправлять задания ученикам, которые не входят в его группы");
        }
        List<StudentEntity> studentEntities = studentRepository.findAllByGroupEntity(groupEntity);
        Assignment assignment = new Assignment()
                .setContent(assignmentDtoRequest.getContent())
                .setGroupEntity(groupEntity)
                .setLessonEntity(lessonEntity)
                .setMaxMark(assignmentDtoRequest.getMaxMark())
                .setSubjectEntity(subjectEntity)
                .setDeadline(assignmentDeadline);
        assignmentRepository.save(assignment);
        assignmentRepository.save(assignment);
        return assignment;

    }

    @Override
    public List<FinishedAssignment> getStudentsFinishedAssignmentsByAssignmentId(Long assignmentId) throws AssignmentException {
        Optional<Assignment> optionalAssignment = assignmentRepository.findById(assignmentId);
        exceptionCheckingUtil.checkForPresentAssignment(optionalAssignment);
        Optional<List<FinishedAssignment>> optionalFinishedAssignments = finishedAssignmentRepository.findAllByAssignment(optionalAssignment.get());
        exceptionCheckingUtil.checkForPresentFinishedAssignments(optionalFinishedAssignments);
        return optionalFinishedAssignments.get();
    }

    @Override
    public MarkEntity setMarkToFinishedAssignment(MarkDtoRequest markDtoRequest) throws AssignmentException, MarkException {
        MarkEntity markEntity = new MarkEntity();
        Optional<FinishedAssignment> optionalFinishedAssignment = finishedAssignmentRepository.findById(markDtoRequest.getFinishedAssignmentId());
        exceptionCheckingUtil.checkForPresentFinishedAssignment(optionalFinishedAssignment);
        FinishedAssignment finishedAssignment = optionalFinishedAssignment.get();
        Assignment assignment = finishedAssignment.getAssignment();
        if (assignment.getMaxMark() < markDtoRequest.getMark()) {
            throw new MarkException("Оценка превышает максимально возможную");
        }
        StudentEntity studentEntity = finishedAssignment.getStudentEntity();
        SubjectEntity subjectEntity = assignment.getSubjectEntity();
        markEntity
                .setFinishedAssignment(finishedAssignment)
                .setStudentEntity(studentEntity)
                .setSubjectEntity(subjectEntity)
                .setMark(markDtoRequest.getMark());
        markRepository.save(markEntity);
        return markEntity;
    }

//    @Override
//    public Assignment setMarkToTheAssignment(Long assignmentId) throws UserNotFoundException, AssignmentException {
//        AppUserEntity appUserEntity = (AppUserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Optional<StudentEntity> optionalStudentEntity = studentRepository.findByAppUserEntity(appUserEntity);
//        exceptionCheckingUtil.checkForPresentStudent(optionalStudentEntity, appUserEntity.getLogin());
//        StudentEntity studentEntity = optionalStudentEntity.get();
//        Optional<Assignment> optionalAssignment = studentEntity.getAssignments()
//                .stream()
//                .filter(assignment -> assignment.getId().equals(assignmentId))
//                .findFirst();
//        if (optionalAssignment.isPresent()) {
//            throw new AssignmentException("Не удалось найти задание!");
//        }
//        Assignment assignment = optionalAssignment.get();
//        if(!assignment.getGroupEntity().equals(studentEntity.getGroupEntity())) {
//            throw new AssignmentException("Задание не было отправлено в вашу группу!");
//        }
//        assignment.setMark(5);
//        assignmentRepository.save(assignment);
//        return assignment;
//    }
}
