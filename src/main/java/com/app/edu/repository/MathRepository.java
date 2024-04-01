package com.app.edu.repository;

import com.app.edu.entities.MathEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MathRepository extends JpaRepository<MathEntity, Integer> {
}
