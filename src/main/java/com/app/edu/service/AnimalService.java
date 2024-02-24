package com.app.edu.service;

import com.app.edu.dtos.AnimalDto;
import java.util.List;
import java.util.Optional;

public interface AnimalService {

    public AnimalDto getAnimalById(Integer id);

    public List<AnimalDto> getAnimalsByAnimalTypeId(Integer animalTypeId);

    public void playSound(Integer id);
}
