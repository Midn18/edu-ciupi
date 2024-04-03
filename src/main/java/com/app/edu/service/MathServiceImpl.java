package com.app.edu.service;

import com.app.edu.dtos.MathDto;
import com.app.edu.entities.MathEntity;
import com.app.edu.repository.MathRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MathServiceImpl implements MathService, MediaResourceService {

    @Autowired
    private MathRepository mathRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<MathDto> getAllMathProblems() {
        List<MathEntity> mathEntities = mathRepository.findAll();
        return mathEntities.stream()
            .map(mathEntity -> modelMapper.map(mathEntity, MathDto.class))
            .collect(Collectors.toList());
    }

    @Override
    public MathDto getMathProblemById(Integer id) {
        return modelMapper.map(mathRepository.findById(id), MathDto.class);
    }

    @Override
    public String compareAnswer(Integer id, String jsonAnswer) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonAnswer);
            String parsedResponse = rootNode.path("answer").asText();

            return mathRepository.findById(id).map(exercise -> {
                if (exercise.getAnswer().equals(parsedResponse)) {
                    return "Corect!";
                } else {
                    return "Incorect!";
                }
            }).orElseThrow(() -> new RuntimeException("Exercise with ID " + id + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse JSON answer", e);
        }
    }

    @Override
    public Resource returnSoundPath(Integer id) {
        return mathRepository.findById(id).map(exercise -> {
            try {
                Path file = Paths.get(exercise.getSoundPath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + exercise.getSoundPath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + exercise.getSoundPath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Exercise with ID " + id + " not found"));
    }

    @Override
    public Resource returnImagePath(Integer id) {
        return mathRepository.findById(id).map(exercise -> {
            try {
                Path file = Paths.get(exercise.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + exercise.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + exercise.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Exercise with ID " + id + " not found"));
    }
}
