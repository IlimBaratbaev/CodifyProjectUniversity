package com.example.universityv2.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    public EquipmentEntity() {
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "equipment_title")
    private String title;
    @Column(name = "auditorium")
    private String auditorium;
    @Column(name = "date_of_receipt")
    private LocalDateTime dateOfReceipt;
    @Column(name = "equipment_condition")
    private String equipmentCondition;
    @ManyToOne
    @JoinColumn(name = "auditorium_id", referencedColumnName = "id")
    private AuditoriumEntity auditoriumEntity;

    public Long getId() {
        return id;
    }

    public EquipmentEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public EquipmentEntity setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getAuditorium() {
        return auditorium;
    }

    public EquipmentEntity setAuditorium(String auditorium) {
        this.auditorium = auditorium;
        return this;
    }

    public LocalDateTime getDateOfReceipt() {
        return dateOfReceipt;
    }

    public EquipmentEntity setDateOfReceipt(LocalDateTime dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
        return this;
    }

    public String getEquipmentCondition() {
        return equipmentCondition;
    }

    public EquipmentEntity setEquipmentCondition(String equipmentCondition) {
        this.equipmentCondition = equipmentCondition;
        return this;
    }

    public AuditoriumEntity getAuditoriumEntity() {
        return auditoriumEntity;
    }

    public EquipmentEntity setAuditoriumEntity(AuditoriumEntity auditoriumEntity) {
        this.auditoriumEntity = auditoriumEntity;
        return this;
    }
}
