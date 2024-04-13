package com.example.universityv2.dto.response;

public class LessonDtoResponse {
    public LessonDtoResponse() {
    }

    private Long id;
    private String teacherFullName;
    private String groupName;
    private String subjectName;
    private String date;
    private String lessonStatus;

    public Long getId() {
        return id;
    }

    public LessonDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public LessonDtoResponse setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public LessonDtoResponse setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public LessonDtoResponse setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public String getDate() {
        return date;
    }

    public LessonDtoResponse setDate(String date) {
        this.date = date;
        return this;
    }

    public String getLessonStatus() {
        return lessonStatus;
    }

    public LessonDtoResponse setLessonStatus(String lessonStatus) {
        this.lessonStatus = lessonStatus;
        return this;
    }
}
