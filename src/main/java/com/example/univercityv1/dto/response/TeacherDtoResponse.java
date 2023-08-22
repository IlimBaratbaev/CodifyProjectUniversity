package com.example.univercityv1.dto.response;


import java.util.List;

public class TeacherDtoResponse {
    private Long id;
    private EmployeeDtoResponse employeeDtoResponse;
    private List<SubjectDtoResponse> subjectDtoResponses;

    public Long getId() {
        return id;
    }

    public TeacherDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public EmployeeDtoResponse getEmployeeDtoResponse() {
        return employeeDtoResponse;
    }

    public TeacherDtoResponse setEmployeeDtoResponse(EmployeeDtoResponse employeeDtoResponse) {
        this.employeeDtoResponse = employeeDtoResponse;
        return this;
    }

    public List<SubjectDtoResponse> getSubjectDtoResponses() {
        return subjectDtoResponses;
    }

    public TeacherDtoResponse setSubjectDtoResponses(List<SubjectDtoResponse> subjectDtoResponses) {
        this.subjectDtoResponses = subjectDtoResponses;
        return this;
    }
}
