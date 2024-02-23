package com.app.edu.controller;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.entities.AnimalEntity;
import com.app.edu.service.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    AnimalService animalService;

    @Operation(summary = "Retrieve all Animals by Animal Category id", tags = {"Animals"})
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AnimalDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "204", description = "There are no animals", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/")
    public ResponseEntity<List<AnimalDto>> getAnimalsByCategoryId(@RequestParam(required = true) Integer animalTypeId) {
        try {
            List<AnimalEntity> animals = animalService.getAnimalsByAnimalTypeId(animalTypeId);
            if (animals.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
