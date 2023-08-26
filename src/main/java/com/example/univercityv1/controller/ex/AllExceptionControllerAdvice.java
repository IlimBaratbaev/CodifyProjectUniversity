package com.example.univercityv1.controller.ex;

import com.example.univercityv1.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.example.univercityv1.controller")
public class AllExceptionControllerAdvice {
    @ExceptionHandler(ApplicationFormException.class)
    public ResponseEntity<Response> handleInvalidApplicationFormException(ApplicationFormException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(AssignmentException.class)
    public ResponseEntity<Response> handleInvalidAssignmentException(AssignmentException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<Response> handleEmployeeException(EmployeeException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Response> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(LessonException.class)
    public ResponseEntity<Response> handleLessonException(LessonException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(OrderException.class)
    public ResponseEntity<Response> handleOrderException(OrderException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(RoleException.class)
    public ResponseEntity<Response> handleRoleException(RoleException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(SubjectException.class)
    public ResponseEntity<Response> handleSubjectException(SubjectException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(TeacherException.class)
    public ResponseEntity<Response> handleTeacherException(TeacherException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Response> handleUserException(UserException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Response> handleUserNotFoundException(UserNotFoundException ex) {
        Response response = new Response(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
