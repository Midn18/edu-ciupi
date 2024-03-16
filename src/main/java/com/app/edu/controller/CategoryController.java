package com.app.edu.controller;

import com.app.edu.dtos.CategoryDto;
import com.app.edu.service.CategoryServiceImpl;
import com.app.edu.utils.AgeCategoryEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Categories", description = "Categories controller")
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryServiceImpl categoryService;

    @Operation(summary = "Retrieve all Categories")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = CategoryDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no categories", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{ageCategory}")
    public ResponseEntity<List<CategoryDto>> getCategories(@PathVariable int ageCategory) {
        try {
            AgeCategoryEnum ageCategoryEnum = AgeCategoryEnum.values()[ageCategory];
            List<CategoryDto> categories = categoryService.getCategories(ageCategoryEnum);

            if (categories.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categories);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
