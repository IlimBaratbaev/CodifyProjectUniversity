package com.example.universityv2.dto.response;

public class FinishedAssignmentDtoResponse {
    public FinishedAssignmentDtoResponse() {
    }

    private Long id;
    private String solution;
    private AssignmentDtoResponse assignmentDtoResponse;
    private StudentDtoResponse studentDtoResponse;

    public Long getId() {
        return id;
    }

    public FinishedAssignmentDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSolution() {
        return solution;
    }

    public FinishedAssignmentDtoResponse setSolution(String solution) {
        this.solution = solution;
        return this;
    }

    public AssignmentDtoResponse getAssignmentDtoResponse() {
        return assignmentDtoResponse;
    }

    public FinishedAssignmentDtoResponse setAssignmentDtoResponse(AssignmentDtoResponse assignmentDtoResponse) {
        this.assignmentDtoResponse = assignmentDtoResponse;
        return this;
    }

    public StudentDtoResponse getStudentDtoResponse() {
        return studentDtoResponse;
    }

    public FinishedAssignmentDtoResponse setStudentDtoResponse(StudentDtoResponse studentDtoResponse) {
        this.studentDtoResponse = studentDtoResponse;
        return this;
    }
}
