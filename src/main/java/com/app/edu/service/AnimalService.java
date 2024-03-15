package com.app.edu.service;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.utils.AnimalTypeEnum;
import java.util.List;
import org.springframework.core.io.Resource;

public interface AnimalService {

    public AnimalDto getAnimalById(Integer id);

    public List<AnimalDto> getAnimalsByAnimalType(AnimalTypeEnum animalType);

    public Resource returnAnimalSoundPath(Integer id);
    public Resource returnAnimalImagePath(Integer id);
}
