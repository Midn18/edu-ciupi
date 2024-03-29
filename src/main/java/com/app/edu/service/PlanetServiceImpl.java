package com.app.edu.service;

import com.app.edu.dtos.PlanetDto;
import com.app.edu.repository.PlanetsRepository;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class PlanetServiceImpl implements PlanetService, MediaResourceService {

    @Autowired
    private PlanetsRepository planetsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PlanetDto getPlanetById(Integer id) {
        return modelMapper.map(planetsRepository.findById(id), PlanetDto.class);
    }

    @Override
    public List<PlanetDto> getAllPlanets() {
        return planetsRepository.findAll()
            .stream()
            .map(planet -> modelMapper.map(planet, PlanetDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return planetsRepository.findById(id).map(planet -> {
            try {
                Path file = Paths.get(planet.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + planet.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + planet.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Planet with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return planetsRepository.findById(id).map(planet -> {
            try {
                Path file = Paths.get(planet.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + planet.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + planet.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Planet with ID " + id + " not found"));
    }
}
