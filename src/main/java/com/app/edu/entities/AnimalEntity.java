package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "e_animal")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnimalEntity extends CommonEntity {

    @Column(name = "animal_type_id")
    private Integer animalTypeId;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;
}