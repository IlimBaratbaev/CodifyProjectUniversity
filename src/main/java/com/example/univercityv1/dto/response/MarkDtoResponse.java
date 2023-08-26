package com.example.univercityv1.dto.response;

import com.example.univercityv1.entity.FinishedAssignment;

public class MarkDtoResponse {
    public MarkDtoResponse() {
    }

    private Long id;
    private Integer mark;
    private StudentDtoResponse studentDtoResponse;
    private FinishedAssignmentDtoResponse finishedAssignmentDtoResponse;

    public Long getId() {
        return id;
    }

    public MarkDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMark() {
        return mark;
    }

    public MarkDtoResponse setMark(Integer mark) {
        this.mark = mark;
        return this;
    }

    public StudentDtoResponse getStudentDtoResponse() {
        return studentDtoResponse;
    }

    public MarkDtoResponse setStudentDtoResponse(StudentDtoResponse studentDtoResponse) {
        this.studentDtoResponse = studentDtoResponse;
        return this;
    }

    public FinishedAssignmentDtoResponse getFinishedAssignmentDtoResponse() {
        return finishedAssignmentDtoResponse;
    }

    public MarkDtoResponse setFinishedAssignmentDtoResponse(FinishedAssignmentDtoResponse finishedAssignmentDtoResponse) {
        this.finishedAssignmentDtoResponse = finishedAssignmentDtoResponse;
        return this;
    }
}
