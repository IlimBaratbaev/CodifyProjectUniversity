package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "employee")
public class EmployeeEntity {
    public EmployeeEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "employee_name")
    private String name;

    @Column(name = "employee_surname")
    private String surname;
    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private DepartmentEntity departmentEntity;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUserEntity appUserEntity;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "position_id", referencedColumnName = "id")
    private PositionEntity positionEntity;

    public Long getId() {
        return id;
    }

    public EmployeeEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public EmployeeEntity setName(String name) {
        this.name = name;
        return this;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public EmployeeEntity setDeleted(Boolean deleted) {
        isDeleted = deleted;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public EmployeeEntity setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public DepartmentEntity getDepartmentEntity() {
        return departmentEntity;
    }

    public EmployeeEntity setDepartmentEntity(DepartmentEntity departmentEntity) {
        this.departmentEntity = departmentEntity;
        return this;
    }

    public AppUserEntity getAppUserEntity() {
        return appUserEntity;
    }

    public EmployeeEntity setAppUserEntity(AppUserEntity appUserEntity) {
        this.appUserEntity = appUserEntity;
        return this;
    }

    public PositionEntity getPositionEntity() {
        return positionEntity;
    }

    public EmployeeEntity setPositionEntity(PositionEntity positionEntity) {
        this.positionEntity = positionEntity;
        return this;
    }
}
