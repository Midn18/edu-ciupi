package com.app.edu.controller;

import com.app.edu.dtos.PlanetDto;
import com.app.edu.service.PlanetServiceImpl;
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
@Tag(name = "Planets", description = "Planets controller")
@RequestMapping("/api/planet")
public class PlanetController {

    @Autowired
    PlanetServiceImpl planetService;

    @Operation(summary = "Retrieve all planets")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PlanetDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no planets", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/all")
    public ResponseEntity<List<PlanetDto>> getPlanets() {
        try {
            List<PlanetDto> planets = planetService.getAllPlanets();
            if (planets.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(planets);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Planet by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = PlanetDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Planet not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<PlanetDto> getSpecificPlanetById(@PathVariable Integer id) {
        try {
            PlanetDto planet = planetService.getPlanetById(id);
            if (planet == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(planet);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve sound path for a planet")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Sound not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/planetSound/{id}")
    public ResponseEntity<?> getPlanetSoundPath(@PathVariable Integer id) {
        try {
            Resource resource = planetService.returnSoundPath(id);
            if (resource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found");
            }

            String filename = resource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve image path for a planet")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "404", description = "Planet not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/planetImage/{id}")
    public ResponseEntity<?> getPlanetImagePath(@PathVariable Integer id) {
        try {
            Resource resource = planetService.returnImagePath(id);
            if (resource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Planet not found");
            }

            String filename = resource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(resource);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
