package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "lesson")
public class LessonEntity {
    public LessonEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private TeacherEntity teacherEntity;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity groupEntity;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subjectEntity;

    @Column(name = "date")
    private LocalDateTime localDateTime;

    @Column(name = "lesson_status")
    private String lessonStatus;

    public Long getId() {
        return id;
    }

    public LessonEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public TeacherEntity getTeacherEntity() {
        return teacherEntity;
    }

    public LessonEntity setTeacherEntity(TeacherEntity teacherEntity) {
        this.teacherEntity = teacherEntity;
        return this;
    }

    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public LessonEntity setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
        return this;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public LessonEntity setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
        return this;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public LessonEntity setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
        return this;
    }

    public String getLessonStatus() {
        return lessonStatus;
    }

    public LessonEntity setLessonStatus(String lessonStatus) {
        this.lessonStatus = lessonStatus;
        return this;
    }
}
