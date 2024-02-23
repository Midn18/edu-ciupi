package com.app.edu.repository;

import com.app.edu.entities.AnimalEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

    public List<AnimalEntity> findAnimalByAnimalTypeId(Integer animalTypeId);
}
