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
    @Column(name = "mark")
    private Integer mark;
    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private SubjectEntity subjectEntity;
    @ManyToOne
    @JoinColumn(name = "finished_assignment_id", referencedColumnName = "id")
    private FinishedAssignment finishedAssignment;


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

    public Integer getMark() {
        return mark;
    }

    public MarkEntity setMark(Integer mark) {
        this.mark = mark;
        return this;
    }

    public SubjectEntity getSubjectEntity() {
        return subjectEntity;
    }

    public MarkEntity setSubjectEntity(SubjectEntity subjectEntity) {
        this.subjectEntity = subjectEntity;
        return this;
    }

    public FinishedAssignment getFinishedAssignment() {
        return finishedAssignment;
    }

    public MarkEntity setFinishedAssignment(FinishedAssignment finishedAssignment) {
        this.finishedAssignment = finishedAssignment;
        return this;
    }
}
