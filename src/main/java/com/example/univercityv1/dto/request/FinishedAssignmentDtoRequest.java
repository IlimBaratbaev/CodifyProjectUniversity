package com.example.univercityv1.dto.request;

public class FinishedAssignmentDtoRequest {
    public FinishedAssignmentDtoRequest() {
    }

    private Long assignmentId;
    private String solution;

    public Long getAssignmentId() {
        return assignmentId;
    }

    public FinishedAssignmentDtoRequest setAssignmentId(Long assignmentId) {
        this.assignmentId = assignmentId;
        return this;
    }

    public String getSolution() {
        return solution;
    }

    public FinishedAssignmentDtoRequest setSolution(String solution) {
        this.solution = solution;
        return this;
    }
}
