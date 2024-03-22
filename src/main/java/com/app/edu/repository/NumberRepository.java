package com.app.edu.repository;

import com.app.edu.entities.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NumberRepository extends JpaRepository<NumberEntity, Integer>{
}
