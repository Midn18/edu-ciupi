package com.app.edu.service;

import java.io.File;
import java.io.FileNotFoundException;

public interface EducationalResourceService {

    public File downloadResource(Integer resourceId) throws FileNotFoundException;
}
