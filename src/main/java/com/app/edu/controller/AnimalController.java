package com.app.edu.controller;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.service.AnimalServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@Tag(name = "Animals", description = "User management APIs")
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    AnimalServiceImpl animalService;

    @Operation(summary = "Retrieve all Animals by Animal Category id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AnimalDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "204", description = "There are no animals", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/")
    public ResponseEntity<List<AnimalDto>> getAnimalsByAnimalTypeId(@RequestParam(required = true) Integer animalTypeId) {
        try {
            List<AnimalDto> animals = animalService.getAnimalsByAnimalTypeId(animalTypeId);
            if (animals.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(animals);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve an Animal by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AnimalDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "204", description = "Animal not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<AnimalDto> getAnimalById(@PathVariable Integer id) {
        try {
            AnimalDto animal = animalService.getAnimalById(id);
            if (animal == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(animal);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Play sound of an animal")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound played successfully", content = {
            @Content(schema = @Schema(implementation = String.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "204", description = "There are no animals", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}/play-sound")
    public ResponseEntity<String> playAnimalSound(@PathVariable Integer id) {
        try {
            animalService.playSound(id);
            return ResponseEntity.ok("Sound played successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
