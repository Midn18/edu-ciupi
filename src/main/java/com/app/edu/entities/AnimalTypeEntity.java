package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "e_animal_type")
public class AnimalTypeEntity {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;
}
