package com.app.edu.repository;

import com.app.edu.entities.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorsRepository extends JpaRepository<ColorEntity, Integer> {
}
