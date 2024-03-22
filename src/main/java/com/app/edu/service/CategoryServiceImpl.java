package com.app.edu.service;

import com.app.edu.dtos.CategoryDto;
import com.app.edu.entities.CategoryEntity;
import com.app.edu.repository.CategoryRepository;
import com.app.edu.utils.AgeCategoryEnum;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

    @Override
    public Resource returnCategoryImagePath(Integer id) {
        return categoryRepository.findById(id).map(category -> {
            try {
                Path file = Paths.get(category.getImagePath());
                Resource resource = new UrlResource(file.toUri());
                if (!resource.exists() || !resource.isReadable()) {
                    throw new RuntimeException("File not found or not readable: " + category.getImagePath());
                }
                return resource;
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error while accessing file: " + category.getImagePath(), e);
            }
        }).orElseThrow(() -> new RuntimeException("Category with ID " + id + " not found"));
    }
}
