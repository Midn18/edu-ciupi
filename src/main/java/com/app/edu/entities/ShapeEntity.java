package com.app.edu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "e_shape")
public class ShapeEntity extends CommonEntity {

    public ShapeEntity(int id, String name, String soundPath, String imagePath) {
        super(id, name, soundPath, imagePath);
    }
}
