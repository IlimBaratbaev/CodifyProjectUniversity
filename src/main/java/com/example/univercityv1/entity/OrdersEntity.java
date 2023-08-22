package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "orders")
public class OrdersEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "order_content")
    private String content;

    @Column(name = "is_done")
    private Boolean isDone;

    @ManyToOne
    @JoinColumn(name = "from_whom", referencedColumnName = "id")
    private EmployeeEntity fromEmployeeEntity;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity toEmployeeEntity;

    public Long getId() {
        return id;
    }

    public OrdersEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public OrdersEntity setContent(String content) {
        this.content = content;
        return this;
    }

    public Boolean getDone() {
        return isDone;
    }

    public OrdersEntity setDone(Boolean done) {
        isDone = done;
        return this;
    }

    public EmployeeEntity getFromEmployeeEntity() {
        return fromEmployeeEntity;
    }

    public OrdersEntity setFromEmployeeEntity(EmployeeEntity fromEmployeeEntity) {
        this.fromEmployeeEntity = fromEmployeeEntity;
        return this;
    }

    public EmployeeEntity getToEmployeeEntity() {
        return toEmployeeEntity;
    }

    public OrdersEntity setToEmployeeEntity(EmployeeEntity toEmployeeEntity) {
        this.toEmployeeEntity = toEmployeeEntity;
        return this;
    }
}
