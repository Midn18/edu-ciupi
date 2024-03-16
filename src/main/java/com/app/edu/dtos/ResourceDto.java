package com.app.edu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDto {

    private Integer id;
    private String name;
    private String resourcePath;
}
