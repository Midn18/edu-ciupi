package com.app.edu.controller;

import com.app.edu.dtos.LetterDto;
import com.app.edu.service.LetterServiceImpl;
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
@Tag(name = "Letters", description = "Letters controller")
@RequestMapping("/api/letter")
public class LetterController {

    @Autowired
    LetterServiceImpl letterService;

    @Operation(summary = "Retrieve all Letters")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LetterDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no letters", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/")
    public ResponseEntity<List<LetterDto>> getLetters() {
        try {
            List<LetterDto> letters = letterService.getAllLetters();
            if (letters.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(letters);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Letter by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = LetterDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Letter not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<LetterDto> getLetterById(Integer id) {
        try {
            LetterDto letter = letterService.getLetterById(id);
            if (letter == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(letter);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve sound path for a letter")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Letter not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/letterSound/{id}")
    public ResponseEntity<Resource> returnLetterSoundPath(@PathVariable Integer id) {
        try {
            Resource soundResource = letterService.returnSoundPath(id);
            if (soundResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letter not found");
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

    @Operation(summary = "Retrieve image path for a letter")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "404", description = "Letter not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/letterImage/{id}")
    public ResponseEntity<Resource> returnLetterImagePath(@PathVariable Integer id) {
        try {
            Resource imageResource = letterService.returnImagePath(id);
            if (imageResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Letter not found");
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

