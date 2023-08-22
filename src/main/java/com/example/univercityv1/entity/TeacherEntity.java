package com.example.univercityv1.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "teacher")
public class TeacherEntity {
    public TeacherEntity() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne()
    @JoinColumn(
            name = "employee_id", referencedColumnName = "id"
    )
    private EmployeeEntity employeeEntity;

    @ManyToMany(mappedBy = "teacherEntities")
    private List<SubjectEntity> subjectEntities;


    public Long getId() {
        return id;
    }

    public TeacherEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public EmployeeEntity getEmployeeEntity() {
        return employeeEntity;
    }

    public TeacherEntity setEmployeeEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
        return this;
    }

    public List<SubjectEntity> getSubjectEntities() {
        return subjectEntities;
    }

    public TeacherEntity setSubjectEntities(List<SubjectEntity> subjectEntities) {
        this.subjectEntities = subjectEntities;
        return this;
    }
}
