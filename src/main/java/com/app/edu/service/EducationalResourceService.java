package com.app.edu.service;

import com.app.edu.dtos.ResourceDto;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface EducationalResourceService {

    List<ResourceDto> getAllResources();

    File downloadResource(Integer resourceId) throws FileNotFoundException;
}
