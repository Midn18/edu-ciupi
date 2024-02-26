package com.app.edu.service;

import com.app.edu.entities.ResourceEntity;
import com.app.edu.repository.EducationalResourceRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationalResourceServiceImpl implements EducationalResourceService {

    @Autowired
    private EducationalResourceRepository educationalResourceRepository;

    @Override
    public File downloadResource(Integer resourceId) throws FileNotFoundException {
        Optional<ResourceEntity> resourceEntityOptional = educationalResourceRepository.findById(resourceId);
        if (resourceEntityOptional.isPresent()) {
            ResourceEntity resourceEntity = resourceEntityOptional.get();
            File file = new File(resourceEntity.getResourcePath());
            if (file.exists()) {
                return file;
            } else {
                throw new FileNotFoundException("File not found");
            }
        } else {
            throw new IllegalArgumentException("Resource not found");
        }
    }
}
