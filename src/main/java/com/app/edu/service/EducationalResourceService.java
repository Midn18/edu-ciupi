package com.app.edu.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface EducationalResourceService {

    List<File> getAllResources();

    File downloadResource(Integer resourceId) throws FileNotFoundException;
}
