package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "e_resource")
public class ResourceEntity {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "resource_path")
    private String resourcePath;
}
