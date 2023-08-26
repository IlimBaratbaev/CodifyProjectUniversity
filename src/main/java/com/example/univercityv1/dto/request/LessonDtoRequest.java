package com.example.univercityv1.dto.request;

public class LessonDtoRequest {
    public LessonDtoRequest() {
    }

    private Long teacherId;
    private String subjectName;
    private Long groupId;
    private String date;

    public Long getTeacherId() {
        return teacherId;
    }

    public LessonDtoRequest setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public LessonDtoRequest setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public Long getGroupId() {
        return groupId;
    }

    public LessonDtoRequest setGroupId(Long groupId) {
        this.groupId = groupId;
        return this;
    }

    public String getDate() {
        return date;
    }

    public LessonDtoRequest setDate(String date) {
        this.date = date;
        return this;
    }
}
