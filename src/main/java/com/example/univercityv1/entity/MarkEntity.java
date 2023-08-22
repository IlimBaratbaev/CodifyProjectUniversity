package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class MarkEntity {
    public MarkEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private ScheduleEntity scheduleEntity;

    public Long getId() {
        return id;
    }

    public MarkEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public MarkEntity setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
        return this;
    }

    public ScheduleEntity getScheduleEntity() {
        return scheduleEntity;
    }

    public MarkEntity setScheduleEntity(ScheduleEntity scheduleEntity) {
        this.scheduleEntity = scheduleEntity;
        return this;
    }
}
