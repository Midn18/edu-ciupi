package com.app.edu.dtos;

import com.app.edu.utils.AnimalTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AnimalDto extends CommonDto {

    private AnimalTypeEnum animalType;
    private String description;
    private String country;
}

