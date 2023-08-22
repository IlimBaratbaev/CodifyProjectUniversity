package com.example.univercityv1.controller;

import com.example.univercityv1.entity.SubjectEntity;
import com.example.univercityv1.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SubjectEntity addSubject(
            @RequestParam(value = "subject") String subject
    ){
        SubjectEntity subjectEntity = deanService.createNewSubject(subject);
        return subjectEntity;
    }
}
