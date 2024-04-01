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
@Table(name = "e_math")
public class MathEntity extends CommonEntity {

    private String problemDescription;
    private String answer;

    public MathEntity(Integer id, String name, String soundPath, String imagePath, String problemDescription, String answer) {
        super(id, name, soundPath, imagePath);
        this.problemDescription = problemDescription;
        this.answer = answer;
    }
}
