package com.example.univercityv1.utils;

import com.example.univercityv1.entity.*;
import com.example.univercityv1.exception.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class ExceptionCheckingUtil {

    public void checkForEmptiness(String login) throws InvalidCredentialsException {
        if (login.isBlank() || Objects.isNull(login)) {
            throw new InvalidCredentialsException("Аргумент не должен быть пустым.");
        }
    }
    public void checkForPresentUser(Optional<AppUserEntity> optionalAppUser, String login) throws UserNotFoundException {
        if (!optionalAppUser.isPresent()) {
            throw new UserNotFoundException(String.format("Нет пользователя с логином %s.", login));
        }
    }
    public void checkForPresentEmployee(Optional<EmployeeEntity> optionalAppUser, String login) throws UserNotFoundException {
        if (!optionalAppUser.isPresent()) {
            throw new UserNotFoundException(String.format("Нет работника с логином %s.", login));
        }
    }
    public void checkForPresentStudent(Optional<StudentEntity> optionalStudentEntity, String login) throws UserNotFoundException {
        if (!optionalStudentEntity.isPresent()) {
            throw new UserNotFoundException(String.format("Нет студента с логином %s.", login));
        }
    }

    public void checkForPresentPosition(Optional<PositionEntity> optionalPositionEntity, String position) throws InvalidCredentialsException {
        if (!optionalPositionEntity.isPresent()) {
            throw new InvalidCredentialsException(String.format("Позиции %s нет.", position));
        }
    }

    public void checkForPresentRole(Optional<AppRoleEntity> optionalAppRoleEntity, String role) throws InvalidCredentialsException, RoleException {
        if (!optionalAppRoleEntity.isPresent()) {
            throw new RoleException(String.format("Роли: %s нет в системе.", role));
        }
    }
    public void checkForPresentOptional(Optional<?> optionalValue, String entityName, String errorMessage) throws InvalidCredentialsException {
        if (!optionalValue.isPresent()) {
            throw new InvalidCredentialsException(String.format("%s %s.", entityName, errorMessage));
        }
    }
    public void checkForPresentTeacher(Optional<TeacherEntity> optionalTeacherEntity) throws TeacherException {
        if (!optionalTeacherEntity.isPresent()) {
            throw new TeacherException("Учитель не найден!");
        }
    }
    public void checkForPresentLesson(Optional<LessonEntity> optionalLessonEntity) throws LessonException {
        if (!optionalLessonEntity.isPresent()) {
            throw new LessonException("Урок не найден!");
        }
    }
    public void checkForPresentSubject(Optional<SubjectEntity> optionalSubjectEntity) throws SubjectException {
        if (!optionalSubjectEntity.isPresent()) {
            throw new SubjectException("Предмет не найден!");
        }
    }
    public void checkForPresentAssignment(Optional<Assignment> optionalAssignment) throws AssignmentException {
        if (!optionalAssignment.isPresent()) {
            throw new AssignmentException("Задание не найдено!");
        }
    }

    public void checkForPresentFinishedAssignments(Optional<List<FinishedAssignment>> optionalFinishedAssignments) throws AssignmentException {
        if (!optionalFinishedAssignments.isPresent()) {
            throw new AssignmentException("Нету отправленных заданий.");
        }
    }
    public void checkForPresentFinishedAssignment(Optional<FinishedAssignment> optionalFinishedAssignment) throws AssignmentException {
        if (!optionalFinishedAssignment.isPresent()) {
            throw new AssignmentException("Нет отправленного задания.");
        }
    }

}
