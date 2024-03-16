package com.app.edu.service;

import com.app.edu.dtos.CategoryDto;
import com.app.edu.utils.AgeCategoryEnum;
import java.util.List;

public interface CategoryService {

    List<CategoryDto> getCategories(AgeCategoryEnum ageCategory);
}
