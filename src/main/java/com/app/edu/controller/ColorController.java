package com.app.edu.controller;

import com.app.edu.dtos.ColorDto;
import com.app.edu.service.ColorServiceImpl;
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
@Tag(name = "Colors", description = "Colors controller")
@RequestMapping("/api/color")
public class ColorController {

    @Autowired
    private ColorServiceImpl colorService;

    @Operation(summary = "Retrieve all Colors")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ColorDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no colors", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/all")
    public ResponseEntity<List<ColorDto>> getColors() {
        try {
            List<ColorDto> colors = colorService.getAllColors();
            if (colors.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(colors);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Color by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ColorDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Color not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<ColorDto> getColorById(@PathVariable Integer id) {
        try {
            ColorDto color = colorService.getColorById(id);
            if (color == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(color);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Color's sound by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Color not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/colorSound/{id}")
    public ResponseEntity<Resource> returnColorSoundPath(@PathVariable Integer id) {
        try {
            Resource soundResource = colorService.returnSoundPath(id);
            if (soundResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Color not found");
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

    @Operation(summary = "Retrieve a Color's image by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/jpeg")}),
        @ApiResponse(responseCode = "404", description = "Color not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/colorImage/{id}")
    public ResponseEntity<Resource> returnColorImagePath(@PathVariable Integer id) {
        try {
            Resource imageResource = colorService.returnImagePath(id);
            if (imageResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Color not found");
            }

            String filename = imageResource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/jpeg"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(imageResource);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
