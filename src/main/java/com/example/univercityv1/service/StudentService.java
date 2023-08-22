package com.example.univercityv1.service;

import com.example.univercityv1.dto.request.ApplicationFormRequest;
import com.example.univercityv1.entity.ApplicationFormEntity;
import com.example.univercityv1.entity.StudentEntity;
import com.example.univercityv1.exception.ApplicationFormException;
import com.example.univercityv1.exception.InvalidCredentialsException;

public interface StudentService {
    ApplicationFormEntity registerToUniversity(ApplicationFormRequest applicationFormRequest, String login) throws InvalidCredentialsException, ApplicationFormException;
}
