package com.app.edu.repository;

import com.app.edu.entities.CategoryEntity;
import com.app.edu.utils.AgeCategoryEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    List<CategoryEntity> findCategoriesByAgeCategory(AgeCategoryEnum ageCategory);
}
