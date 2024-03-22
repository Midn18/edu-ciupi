package com.app.edu.service;

import com.app.edu.dtos.LetterDto;
import com.app.edu.entities.LetterEntity;
import com.app.edu.repository.LetterRepository;
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
public class LetterServiceImpl implements LetterService, MediaResourceService {

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LetterDto getLetterById(Integer id) {
        return modelMapper.map(letterRepository.findById(id), LetterDto.class);
    }

    @Override
    public List<LetterDto> getAllLetters() {
        List<LetterDto> letterDtos = new ArrayList<>();
        for (LetterEntity letterEntity : letterRepository.findAll()) {
            letterDtos.add(modelMapper.map(letterEntity, LetterDto.class));
        }
        return letterDtos;
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return letterRepository.findById(id).map(letter -> {
            try {
                Path file = Paths.get(letter.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + letter.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + letter.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Letter with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return letterRepository.findById(id).map(letter -> {
            try {
                Path file = Paths.get(letter.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + letter.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + letter.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Letter with ID " + id + " not found"));
    }
}
