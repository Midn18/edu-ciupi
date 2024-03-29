package com.app.edu.repository;

import com.app.edu.entities.PlanetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanetsRepository extends JpaRepository<PlanetEntity, Integer> {
}
