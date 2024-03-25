package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "e_science")
public class ScienceEntity extends CommonEntity{

    @Column(name = "description")
    private String description;
}
