package com.app.edu.entities;

import com.app.edu.utils.AnimalTypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

    @Enumerated(EnumType.ORDINAL)
    private AnimalTypeEnum animalType;

    @Column(name = "description")
    private String description;

    @Column(name = "country")
    private String country;
}