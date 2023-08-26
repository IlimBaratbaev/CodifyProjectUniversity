package com.example.univercityv1.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(schema = "public", name = "finished_assignment")
public class FinishedAssignment {
    public FinishedAssignment() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "solution")
    private String solution;
    @Column(name = "sent_time")
    private LocalDateTime sentTime;
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;
    @ManyToOne
    @JoinColumn(name = "assignment_id", referencedColumnName = "id")
    private Assignment assignment;

    public Long getId() {
        return id;
    }

    public FinishedAssignment setId(Long id) {
        this.id = id;
        return this;
    }

    public String getSolution() {
        return solution;
    }

    public FinishedAssignment setSolution(String solution) {
        this.solution = solution;
        return this;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public FinishedAssignment setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
        return this;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public FinishedAssignment setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
        return this;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public FinishedAssignment setAssignment(Assignment assignment) {
        this.assignment = assignment;
        return this;
    }

}
