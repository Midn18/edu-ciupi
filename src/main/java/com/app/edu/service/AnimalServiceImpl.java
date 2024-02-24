package com.app.edu.service;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.entities.AnimalEntity;
import com.app.edu.repository.AnimalRepository;
import com.app.edu.utils.SoundManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SoundManager soundManager;

    @Override
    public AnimalDto getAnimalById(Integer id) {
        return modelMapper.map(animalRepository.findById(id), AnimalDto.class);
    }

    @Override
    public List<AnimalDto> getAnimalsByAnimalTypeId(Integer animalTypeId) {
        List<AnimalEntity> animalEntities = animalRepository.findAnimalByAnimalTypeId(animalTypeId);
        List<AnimalDto> animalDtos = new ArrayList<>();

        for (AnimalEntity animalEntity : animalEntities) {
            animalDtos.add(modelMapper.map(animalEntity, AnimalDto.class));
        }
        return animalDtos;
    }

    @Override
    public void playSound(Integer id) {
        Optional<AnimalEntity> animalEntity = animalRepository.findById(id);
        animalEntity.ifPresent(entity -> soundManager.playSound(entity.getSoundPath()));
    }

}
