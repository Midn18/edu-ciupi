package com.app.edu.service;

import com.app.edu.dtos.ShapeDto;
import com.app.edu.repository.ShapeRepository;
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
public class ShapeServiceImpl implements ShapeService, MediaResourceService {

    @Autowired
    private ShapeRepository shapeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShapeDto getShapeById(Integer id) {
        return modelMapper.map(shapeRepository.findById(id), ShapeDto.class);
    }

    @Override
    public List<ShapeDto> getAllShapes() {
        return shapeRepository.findAll()
            .stream()
            .map(shape -> modelMapper.map(shape, ShapeDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return shapeRepository.findById(id).map(shape -> {
            try {
                Path file = Paths.get(shape.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + shape.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + shape.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Shape with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return shapeRepository.findById(id).map(shape -> {
            try {
                Path file = Paths.get(shape.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + shape.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + shape.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Shape with ID " + id + " not found"));
    }
}
