package com.app.edu.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlanetDto extends CommonDto {

    private String description;
}
