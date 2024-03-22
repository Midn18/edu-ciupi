package com.app.edu.controller;

import com.app.edu.dtos.NumberDto;
import com.app.edu.service.NumberServiceImpl;
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
@Tag(name = "Numbers", description = "Numbers controller")
@RequestMapping("/api/number")
public class NumberController {

    @Autowired
    NumberServiceImpl numberService;

    @Operation(summary = "Retrieve all numbers")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = NumberDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "There are no numbers", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/")
    public ResponseEntity<List<NumberDto>> getNumbers() {
        try {
            List<NumberDto> numbers = numberService.getAllNumbers();
            if (numbers.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(numbers);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve a Number by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = NumberDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "Number not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<NumberDto> getNumberById(Integer id) {
        try {
            NumberDto number = numberService.getNumberById(id);
            if (number == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(number);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @Operation(summary = "Retrieve sound path for a Number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Sound file returned successfully", content = {
            @Content(mediaType = "audio/mpeg")}),
        @ApiResponse(responseCode = "404", description = "Number not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/numberSound/{id}")
    public ResponseEntity<Resource> returnNumberSoundPath(@PathVariable Integer id) {
        try {
            Resource soundResource = numberService.returnSoundPath(id);
            if (soundResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Number not found");
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

    @Operation(summary = "Retrieve image path for a Number")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Image file returned successfully", content = {
            @Content(mediaType = "image/png")}),
        @ApiResponse(responseCode = "404", description = "Number not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/numberImage/{id}")
    public ResponseEntity<Resource> returnNumberImagePath(@PathVariable Integer id) {
        try {
            Resource imageResource = numberService.returnImagePath(id);
            if (imageResource == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Number not found");
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
