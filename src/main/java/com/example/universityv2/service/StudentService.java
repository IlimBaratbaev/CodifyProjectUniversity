package com.example.universityv2.service;

import com.example.universityv2.dto.request.ApplicationFormRequest;
import com.example.universityv2.dto.request.FinishedAssignmentDtoRequest;
import com.example.universityv2.entity.*;
import com.example.universityv2.exception.*;

import java.util.List;

public interface StudentService {
    ApplicationFormEntity registerToUniversity(ApplicationFormRequest applicationFormRequest, String login) throws InvalidCredentialsException, ApplicationFormException;
    List<ScheduleEntity> getMyStudentSchedule() throws UserNotFoundException;
    List<Assignment> getMyAssignmentsBySubjectName(String subjectName) throws InvalidCredentialsException, SubjectException, UserNotFoundException;
    FinishedAssignment sendAssignment(FinishedAssignmentDtoRequest finishedAssignmentDtoRequest) throws UserNotFoundException, AssignmentException;
}
