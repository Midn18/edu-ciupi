package com.app.edu.service;

import com.app.edu.dtos.NumberDto;
import com.app.edu.repository.NumberRepository;
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
public class NumberServiceImpl implements NumberService, MediaResourceService {

    @Autowired
    private NumberRepository numberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public NumberDto getNumberById(Integer id) {
        return modelMapper.map(numberRepository.findById(id), NumberDto.class);
    }

    @Override
    public List<NumberDto> getAllNumbers() {
        return numberRepository.findAll()
            .stream()
            .map(number -> modelMapper.map(number, NumberDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return numberRepository.findById(id).map(number -> {
            try {
                Path file = Paths.get(number.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + number.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + number.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Number with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return numberRepository.findById(id).map(number -> {
            try {
                Path file = Paths.get(number.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + number.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + number.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Number with ID " + id + " not found"));
    }
}
