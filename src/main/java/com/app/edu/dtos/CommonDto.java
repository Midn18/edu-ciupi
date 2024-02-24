package com.app.edu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommonDto {

    private Integer id;
    private String name;
    private String soundPath;
    private String imagePath;
}