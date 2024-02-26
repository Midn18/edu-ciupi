package com.app.edu.repository;

import com.app.edu.entities.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationalResourceRepository extends JpaRepository<ResourceEntity, Integer> {
}
