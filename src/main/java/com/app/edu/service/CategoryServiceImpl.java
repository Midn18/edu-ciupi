package com.app.edu.service;

import com.app.edu.dtos.CategoryDto;
import com.app.edu.entities.CategoryEntity;
import com.app.edu.repository.CategoryRepository;
import com.app.edu.utils.AgeCategoryEnum;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CategoryDto> getCategories(AgeCategoryEnum ageCategory) {
        List<CategoryEntity> categoryEntities = categoryRepository.findCategoriesByAgeCategory(ageCategory);
        List<CategoryDto> categoryDtos = new ArrayList<>();

        for (CategoryEntity categoryEntity : categoryEntities) {
            categoryDtos.add(modelMapper.map(categoryEntity, CategoryDto.class));
        }
        return categoryDtos;
    }
}
