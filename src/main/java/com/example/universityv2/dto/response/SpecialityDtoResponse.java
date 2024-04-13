package com.example.universityv2.dto.response;

import java.util.List;

public class SpecialityDtoResponse {
    public SpecialityDtoResponse() {
    }
    private Long id;
    private String specialityTitle;
    private List<SubjectDtoResponse> subjectDtoResponses;

    public Long getId() {
        return id;
    }

    public SpecialityDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSpecialityTitle() {
        return specialityTitle;
    }

    public SpecialityDtoResponse setSpecialityTitle(String specialityTitle) {
        this.specialityTitle = specialityTitle;
        return this;
    }

    public List<SubjectDtoResponse> getSubjectDtoResponses() {
        return subjectDtoResponses;
    }

    public SpecialityDtoResponse setSubjectDtoResponses(List<SubjectDtoResponse> subjectDtoResponses) {
        this.subjectDtoResponses = subjectDtoResponses;
        return this;
    }
}
