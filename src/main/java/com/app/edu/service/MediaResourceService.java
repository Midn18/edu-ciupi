package com.app.edu.service;

import org.springframework.core.io.Resource;

public interface MediaResourceService {

    Resource returnSoundPath(Integer id);

    Resource returnImagePath(Integer id);
}
