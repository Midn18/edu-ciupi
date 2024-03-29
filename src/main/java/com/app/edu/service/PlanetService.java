package com.app.edu.service;

import com.app.edu.dtos.PlanetDto;
import java.util.List;

public interface PlanetService {

    List<PlanetDto> getAllPlanets();

    PlanetDto getPlanetById(Integer id);
}
