package com.app.edu.repository;

import com.app.edu.entities.LetterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<LetterEntity, Integer> {
}
