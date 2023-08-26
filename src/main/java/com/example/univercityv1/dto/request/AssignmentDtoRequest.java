package com.example.univercityv1.dto.request;

public class AssignmentDtoRequest {
    public AssignmentDtoRequest() {
    }

    private String content;
    private Integer maxMark;
    private Long groupId;
    private String subjectName;
    private Long lessonId;
    private String deadline;

    public String getContent() {
        return content;
    }

    public AssignmentDtoRequest setContent(String content) {
        this.content = content;
        return this;
    }

    public Integer getMaxMark() {
        return maxMark;
    }

    public AssignmentDtoRequest setMaxMark(Integer maxMark) {
        this.maxMark = maxMark;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public AssignmentDtoRequest setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public AssignmentDtoRequest setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public AssignmentDtoRequest setLessonId(Long lessonId) {
        this.lessonId = lessonId;
        return this;
    }

    public String getDeadline() {
        return deadline;
    }

    public AssignmentDtoRequest setDeadline(String deadline) {
        this.deadline = deadline;
        return this;
    }
}
