package com.app.edu.repository;

import com.app.edu.entities.ShapeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository extends JpaRepository<ShapeEntity, Integer> {
}
