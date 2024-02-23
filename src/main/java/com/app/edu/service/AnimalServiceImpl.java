package com.app.edu.service;

import com.app.edu.entities.AnimalEntity;
import com.app.edu.repository.AnimalRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public Optional<AnimalEntity> getAnimalById(Integer id) {
        return animalRepository.findById(id);
    }

    @Override
    public List<AnimalEntity> getAnimalsByAnimalTypeId(Integer animalTypeId) {
        return animalRepository.findAnimalByAnimalTypeId(animalTypeId);
    }
}
