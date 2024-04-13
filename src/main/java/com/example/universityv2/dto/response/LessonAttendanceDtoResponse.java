package com.example.universityv2.dto.response;

public class LessonAttendanceDtoResponse {
    private Long id;
    private Boolean isPresent;
    private StudentDtoResponse studentDtoResponse;
    private LessonDtoResponse lessonDtoResponse;

    public Long getId() {
        return id;
    }

    public LessonAttendanceDtoResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public LessonAttendanceDtoResponse setPresent(Boolean present) {
        isPresent = present;
        return this;
    }

    public StudentDtoResponse getStudentDtoResponse() {
        return studentDtoResponse;
    }

    public LessonAttendanceDtoResponse setStudentDtoResponse(StudentDtoResponse studentDtoResponse) {
        this.studentDtoResponse = studentDtoResponse;
        return this;
    }

    public LessonDtoResponse getLessonDtoResponse() {
        return lessonDtoResponse;
    }

    public LessonAttendanceDtoResponse setLessonDtoResponse(LessonDtoResponse lessonDtoResponse) {
        this.lessonDtoResponse = lessonDtoResponse;
        return this;
    }
}
