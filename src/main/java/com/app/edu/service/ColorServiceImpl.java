package com.app.edu.service;

import com.app.edu.dtos.ColorDto;
import com.app.edu.repository.ColorsRepository;
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
public class ColorServiceImpl implements ColorService, MediaResourceService {

    @Autowired
    private ColorsRepository colorsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ColorDto> getAllColors() {
        return colorsRepository.findAll().stream()
            .map(color -> modelMapper.map(color, ColorDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public ColorDto getColorById(Integer id) {
        return modelMapper.map(colorsRepository.findById(id), ColorDto.class);
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return colorsRepository.findById(id).map(color -> {
            try {
                Path file = Paths.get(color.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + color.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + color.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Color with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return colorsRepository.findById(id).map(color -> {
            try {
                Path file = Paths.get(color.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + color.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + color.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Color with ID " + id + " not found"));
    }
}
