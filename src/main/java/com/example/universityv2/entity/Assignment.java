package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "public", name = "student_assignment")
public class Assignment {
    public Assignment() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;
    @Column(name = "max_mark")
    private Integer maxMark;

    @Column(name = "deadline")
    private LocalDateTime deadline;
    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private GroupEntity groupEntity;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subjectEntity;
    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    public Long getId() {
        return id;
    }

    public Assignment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Assignment setContent(String content) {
        this.content = content;
        return this;
    }
    public GroupEntity getGroupEntity() {
        return groupEntity;
    }

    public Assignment setGroupEntity(GroupEntity groupEntity) {
        this.groupEntity = groupEntity;
        return this;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public Assignment setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
        return this;
    }



    public Integer getMaxMark() {
        return maxMark;
    }

    public Assignment setMaxMark(Integer maxMark) {
        this.maxMark = maxMark;
        return this;
    }

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    public Assignment setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
        return this;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public Assignment setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
        return this;
    }
}
