package com.app.edu.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "e_letter")
public class LetterEntity extends CommonEntity {

    public LetterEntity(int id, String name, String soundPath, String imagePath) {
        super(id, name, soundPath, imagePath);
    }
}
