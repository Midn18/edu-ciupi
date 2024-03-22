package com.app.edu.dtos;

import com.app.edu.utils.AgeCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer id;
    private String name;
    private String imagePath;
    private String description;
    private AgeCategoryEnum ageCategory;
}
