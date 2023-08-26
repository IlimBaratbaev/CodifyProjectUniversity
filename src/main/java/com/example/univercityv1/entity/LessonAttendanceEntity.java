package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "lesson_attendance")
public class LessonAttendanceEntity {
    public LessonAttendanceEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "is_present")
    private Boolean isPresent;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    public Long getId() {
        return id;
    }

    public LessonAttendanceEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Boolean getPresent() {
        return isPresent;
    }

    public LessonAttendanceEntity setPresent(Boolean present) {
        isPresent = present;
        return this;
    }

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    public LessonAttendanceEntity setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
        return this;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public LessonAttendanceEntity setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
        return this;
    }
}
