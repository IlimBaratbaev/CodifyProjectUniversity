package com.example.universityv2.service.impl;

import com.example.universityv2.entity.SubjectEntity;
import com.example.universityv2.repository.SubjectRepository;
import com.example.universityv2.service.DeanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeanServiceImpl implements DeanService {

    private final SubjectRepository subjectRepository;
    @Autowired
    public DeanServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public SubjectEntity createNewSubject(String subject) {
        SubjectEntity subjectEntity = new SubjectEntity().setTitle(subject);
        subjectRepository.save(subjectEntity);
        return subjectEntity;
    }
}
