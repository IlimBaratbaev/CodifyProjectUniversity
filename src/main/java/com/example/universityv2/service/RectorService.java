package com.example.universityv2.service;

import com.example.universityv2.entity.ApplicationFormEntity;
import com.example.universityv2.exception.ApplicationFormException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.UserNotFoundException;

public interface RectorService {
    ApplicationFormEntity admissionDecision(Long applicationFormId, Boolean decision, String secretaryLogin, String fromWhom) throws InvalidCredentialsException, UserNotFoundException, ApplicationFormException;
}
