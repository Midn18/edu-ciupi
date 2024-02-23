package com.app.edu.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Data;

@Data
@MappedSuperclass
public class CommonEntity implements Serializable {

    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "sound_path")
    private String soundPath;

    @Column(name = "image_path")
    private String imagePath;
}
