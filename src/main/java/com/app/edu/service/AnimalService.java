package com.app.edu.service;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.utils.AnimalTypeEnum;
import java.util.List;

public interface AnimalService {

    AnimalDto getAnimalById(Integer id);

    List<AnimalDto> getAnimalsByAnimalType(AnimalTypeEnum animalType);
}
