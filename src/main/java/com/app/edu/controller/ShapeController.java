package com.app.edu.controller;

import com.app.edu.dtos.ShapeDto;
import com.app.edu.service.ShapeServiceImpl;
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
@Tag(name = "Shapes", description = "Shapes controller")
@RequestMapping("/api/shape")
public class ShapeController {

    @Autowired
    ShapeServiceImpl shapeService;

    @Operation(summary = "Retrieve all shapes")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ShapeDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no shapes", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/all")
    public ResponseEntity<List<ShapeDto>> getShapes() {
        try {
            List<ShapeDto> shapes = shapeService.getAllShapes();
            if (shapes.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shapes);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Shape by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = ShapeDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Shape not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<ShapeDto> getShapeById(Integer id) {
        try {
            ShapeDto shape = shapeService.getShapeById(id);
            if (shape == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(shape);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve sound path for a Shape")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Shape not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/shapeSound/{id}")
    public ResponseEntity<Resource> returnShapeSoundPath(@PathVariable Integer id) {
        try {
            Resource soundResource = shapeService.returnSoundPath(id);
            if (soundResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shape not found");
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

    @Operation(summary = "Retrieve the image path of a Shape by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "404", description = "Shape not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/shapeImage/{id}")
    public ResponseEntity<Resource> returnShapeImagePath(@PathVariable Integer id) {
        try {
            Resource imageResource = shapeService.returnImagePath(id);
            if (imageResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Shape not found");
            }

            String filename = imageResource.getFilename();
            return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + filename + "\"")
                .body(imageResource);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
