package com.example.universityv2.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

    public ScheduleEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    private LessonEntity lessonEntity;

    public Long getId() {
        return id;
    }

    public ScheduleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LessonEntity getLessonEntity() {
        return lessonEntity;
    }

    public ScheduleEntity setLessonEntity(LessonEntity lessonEntity) {
        this.lessonEntity = lessonEntity;
        return this;
    }
}
