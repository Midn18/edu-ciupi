package com.app.edu.service;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.entities.AnimalEntity;
import com.app.edu.repository.AnimalRepository;
import com.app.edu.utils.AnimalTypeEnum;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AnimalDto getAnimalById(Integer id) {
        return modelMapper.map(animalRepository.findById(id), AnimalDto.class);
    }

    @Override
    public List<AnimalDto> getAnimalsByAnimalType(AnimalTypeEnum animalType) {
        List<AnimalEntity> animalEntities = animalRepository.findAnimalByAnimalType(animalType);
        List<AnimalDto> animalDtos = new ArrayList<>();

        for (AnimalEntity animalEntity : animalEntities) {
            animalDtos.add(modelMapper.map(animalEntity, AnimalDto.class));
        }
        return animalDtos;
    }

    @Override
    public Resource returnAnimalSoundPath(Integer id) {
        return animalRepository.findById(id).map(animal -> {
            try {
                Path file = Paths.get(animal.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + animal.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + animal.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Animal with ID " + id + " not found"));
    }

    @Override
    public Resource returnAnimalImagePath(Integer id) {
        return animalRepository.findById(id).map(animal -> {
            try {
                Path file = Paths.get(animal.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("Image not found or not readable: " + animal.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing image: " + animal.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Animal with ID " + id + " not found"));
    }
}