package com.app.edu.repository;

import com.app.edu.entities.AnimalEntity;
import com.app.edu.utils.AnimalTypeEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<AnimalEntity, Integer> {

    List<AnimalEntity> findAnimalByAnimalType(AnimalTypeEnum animalType);
}
