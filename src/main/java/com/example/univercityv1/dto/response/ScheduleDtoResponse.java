package com.example.univercityv1.dto.response;

public class ScheduleDtoResponse {
    public ScheduleDtoResponse() {
    }
    private Long id;
    private Long lessonId;
    private String groupName;
    private String subjectName;
    private String teacherFullName;
    private String time;

    public Long getId() {
        return id;
    }

    public ScheduleDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public ScheduleDtoResponse setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public ScheduleDtoResponse setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public String getTeacherFullName() {
        return teacherFullName;
    }

    public ScheduleDtoResponse setTeacherFullName(String teacherFullName) {
        this.teacherFullName = teacherFullName;
        return this;
    }

    public String getTime() {
        return time;
    }

    public ScheduleDtoResponse setTime(String time) {
        this.time = time;
        return this;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public ScheduleDtoResponse setLessonId(Long lessonId) {
        this.lessonId = lessonId;
        return this;
    }
}
