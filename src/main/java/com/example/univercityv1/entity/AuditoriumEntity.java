package com.example.univercityv1.entity;


import javax.persistence.*;

@Entity
@Table(schema = "public", name = "auditorium")
public class AuditoriumEntity {
    public AuditoriumEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number")
    private Integer number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private StudentEntity studentEntity;

    public Long getId() {
        return id;
    }

    public AuditoriumEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public AuditoriumEntity setNumber(Integer number) {
        this.number = number;
        return this;
    }

    public StudentEntity getStudentEntity() {
        return studentEntity;
    }

    public AuditoriumEntity setStudentEntity(StudentEntity studentEntity) {
        this.studentEntity = studentEntity;
        return this;
    }
}
