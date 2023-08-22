package com.example.univercityv1.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {
    //Доработать расписание
    public ScheduleEntity() {
    }
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
