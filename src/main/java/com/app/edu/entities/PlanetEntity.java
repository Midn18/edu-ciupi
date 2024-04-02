package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_planet")
public class PlanetEntity extends CommonEntity{

    @Lob
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public PlanetEntity(int id, String name, String soundPath, String imagePath, String description) {
        super(id, name, soundPath, imagePath);
        this.description = description;
    }
}
