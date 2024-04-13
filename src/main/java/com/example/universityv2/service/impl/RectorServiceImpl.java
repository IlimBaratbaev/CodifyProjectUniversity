package com.example.universityv2.service.impl;

import com.example.universityv2.dto.request.OrderDtoRequest;
import com.example.universityv2.dto.response.OrderDtoResponse;
import com.example.universityv2.entity.ApplicationFormEntity;
import com.example.universityv2.entity.EmployeeEntity;
import com.example.universityv2.entity.StudentEntity;
import com.example.universityv2.exception.ApplicationFormException;
import com.example.universityv2.exception.InvalidCredentialsException;
import com.example.universityv2.exception.UserNotFoundException;
import com.example.universityv2.repository.ApplicationFormRepository;
import com.example.universityv2.repository.EmployeeRepository;
import com.example.universityv2.repository.StudentRepository;
import com.example.universityv2.service.MessageService;
import com.example.universityv2.service.OrderService;
import com.example.universityv2.service.RectorService;
import com.example.universityv2.utils.ExceptionCheckingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RectorServiceImpl implements RectorService {
    private final ApplicationFormRepository applicationFormRepository;
    private final ExceptionCheckingUtil exceptionCheckingUtil;
    private final OrderService orderService;
    private final EmployeeRepository employeeRepository;
    private final MessageService messageService;
    private final StudentRepository studentRepository;

    @Autowired
    public RectorServiceImpl(ApplicationFormRepository applicationFormRepository, ExceptionCheckingUtil exceptionCheckingUtil, OrderService orderService, EmployeeRepository employeeRepository, MessageService messageService, StudentRepository studentRepository) {
        this.applicationFormRepository = applicationFormRepository;
        this.exceptionCheckingUtil = exceptionCheckingUtil;
        this.orderService = orderService;
        this.employeeRepository = employeeRepository;
        this.messageService = messageService;
        this.studentRepository = studentRepository;
    }

    @Override
    public ApplicationFormEntity admissionDecision(Long applicationFormId, Boolean decision, String secretaryLogin, String fromWhom) throws InvalidCredentialsException, UserNotFoundException, ApplicationFormException {
        Optional<ApplicationFormEntity> optionalApplicationFormEntity = applicationFormRepository.findById(applicationFormId);
        exceptionCheckingUtil.checkForPresentOptional(optionalApplicationFormEntity, "Заяление", "не найдено");
        ApplicationFormEntity applicationFormEntity = optionalApplicationFormEntity.get();
        Optional<EmployeeEntity> optionalEmployeeEntity = employeeRepository.findByAppUserEntity_Login(fromWhom);
        exceptionCheckingUtil.checkForPresentEmployee(optionalEmployeeEntity, fromWhom);
        if (decision.equals(true)) {
            applicationFormEntity.setApproved(true);
            applicationFormRepository.save(applicationFormEntity);
            OrderDtoRequest orderDtoRequest = new OrderDtoRequest();
            orderDtoRequest
                    .setContent(String.format("Зарегистрировать студента: %s в факультете %s ",
                        applicationFormEntity.getAppUserEntity(),
                        applicationFormEntity.getFacultyEntity().getTitle()))
                    .setToWhomLogin(secretaryLogin);
            OrderDtoResponse ordersEntity = orderService.sendOrder(orderDtoRequest, optionalEmployeeEntity.get());
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setAppUserEntity(applicationFormEntity.getAppUserEntity())
                    .setName(applicationFormEntity.getAppUserEntity().getName())
                    .setSurname(applicationFormEntity.getAppUserEntity().getSurname());
            studentRepository.save(studentEntity);
            return applicationFormEntity;
        }
        if (decision.equals(false)) {
            applicationFormEntity.setApproved(false);
            OrderDtoRequest orderDtoRequest = new OrderDtoRequest();
            orderDtoRequest
                    .setContent(String.format("Оповестить об отказе зачисления %s.",
                            applicationFormEntity.getAppUserEntity()))
                    .setToWhomLogin(secretaryLogin);
            OrderDtoResponse ordersEntity = orderService.sendOrder(orderDtoRequest, optionalEmployeeEntity.get());

            applicationFormRepository.save(applicationFormEntity);
//            messageService.sendMessageNonEnrollment(fromWhom, applicationFormEntity.getAppUserEntity().getLogin());
            return applicationFormEntity;
        }
         throw new ApplicationFormException("Не получилось подтвердить зачисление студента в университет!");
    }
}
