package com.app.edu.controller;

import com.app.edu.dtos.AnimalDto;
import com.app.edu.service.AnimalServiceImpl;
import com.app.edu.utils.AnimalTypeEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@Tag(name = "Animals", description = "Animals management APIs")
@RequestMapping("/api/animals")
public class AnimalController {

    @Autowired
    AnimalServiceImpl animalService;

    @Operation(summary = "Retrieve all Animals by Animal Type")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = AnimalDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "204", description = "There are no animals", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/type/{animalType}")
    public ResponseEntity<List<AnimalDto>> getAnimalsByAnimalType(@PathVariable int animalType) {
        try {
            AnimalTypeEnum animalTypeEnum = AnimalTypeEnum.values()[animalType];
            List<AnimalDto> animals = animalService.getAnimalsByAnimalType(animalTypeEnum);
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
        @ApiResponse(responseCode = "404", description = "Animal not found", content = {
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

    @Operation(summary = "Get an animal sound file by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Animal not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/animalSound/{id}")
    public ResponseEntity<Resource> returnAnimalSoundPath(@PathVariable Integer id) {
        try {
            Resource soundResource = animalService.returnAnimalSoundPath(id);
            if (soundResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
            }

            String filename = soundResource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("audio/mpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(soundResource);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Get an animal image file by ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "404", description = "Animal not found"),
        @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(value = "/animalImage/{id}")
    public ResponseEntity<Resource> returnAnimalImagePath(@PathVariable Integer id) {
        try {
            Resource imageResource = animalService.returnAnimalImagePath(id);
            if (imageResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Animal not found");
            }

            String filename = imageResource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png")) // Adjust based on your image format
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(imageResource);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
