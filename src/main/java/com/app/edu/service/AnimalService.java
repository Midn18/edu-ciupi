package com.app.edu.service;

import com.app.edu.entities.AnimalEntity;
import java.util.List;
import java.util.Optional;

public interface AnimalService {

    public Optional<AnimalEntity> getAnimalById(Integer id);

    public List<AnimalEntity> getAnimalsByAnimalTypeId(Integer animalTypeId);
}
