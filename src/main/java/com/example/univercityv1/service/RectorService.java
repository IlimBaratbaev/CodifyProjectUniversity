package com.example.univercityv1.service;

import com.example.univercityv1.entity.ApplicationFormEntity;
import com.example.univercityv1.exception.ApplicationFormException;
import com.example.univercityv1.exception.InvalidCredentialsException;
import com.example.univercityv1.exception.UserNotFoundException;

public interface RectorService {
    ApplicationFormEntity admissionDecision(Long applicationFormId, Boolean decision, String secretaryLogin, String fromWhom) throws InvalidCredentialsException, UserNotFoundException, ApplicationFormException;
}
