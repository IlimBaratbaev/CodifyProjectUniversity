package com.example.universityv2.dto.request;

public class MarkDtoRequest {
    public MarkDtoRequest() {
    }

    private Integer mark;
    private Long finishedAssignmentId;

    public Integer getMark() {
        return mark;
    }

    public MarkDtoRequest setMark(Integer mark) {
        this.mark = mark;
        return this;
    }

    public Long getFinishedAssignmentId() {
        return finishedAssignmentId;
    }

    public MarkDtoRequest setFinishedAssignmentId(Long finishedAssignmentId) {
        this.finishedAssignmentId = finishedAssignmentId;
        return this;
    }
}
