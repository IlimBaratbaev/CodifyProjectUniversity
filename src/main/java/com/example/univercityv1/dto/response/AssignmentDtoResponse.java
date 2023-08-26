package com.example.univercityv1.dto.response;

import java.time.LocalDateTime;

public class AssignmentDtoResponse {
    public AssignmentDtoResponse() {
    }

    private Long id;
    private String content;
    private Integer maxMark;
    private LessonDtoResponse lessonDtoResponse;
    private LocalDateTime deadline;

    public Long getId() {
        return id;
    }

    public AssignmentDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public AssignmentDtoResponse setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getMaxMark() {
        return maxMark;
    }

    public AssignmentDtoResponse setMaxMark(Integer maxMark) {
        this.maxMark = maxMark;
        return this;
    }

    public LessonDtoResponse getLessonDtoResponse() {
        return lessonDtoResponse;
    }

    public AssignmentDtoResponse setLessonDtoResponse(LessonDtoResponse lessonDtoResponse) {
        this.lessonDtoResponse = lessonDtoResponse;
        return this;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public AssignmentDtoResponse setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }
}
