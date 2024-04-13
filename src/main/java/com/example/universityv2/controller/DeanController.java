package com.example.universityv2.controller;

import com.example.universityv2.entity.SubjectEntity;
import com.example.universityv2.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dean")
public class DeanController {
    @Autowired
    public DeanController(DeanService deanService) {
        this.deanService = deanService;
    }
    private final DeanService deanService;

    @PostMapping(value = "/add/subject")
    public ResponseEntity<SubjectEntity> addSubject(
            @RequestParam(value = "subject") String subject
    ){
        SubjectEntity subjectEntity = deanService.createNewSubject(subject);
        return new ResponseEntity<>(subjectEntity, HttpStatus.CREATED);
    }
}
