package com.app.edu.controller;

import com.app.edu.dtos.UserDto;
import com.app.edu.entities.UserEntity;
import com.app.edu.service.UserService;
import com.app.edu.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

//    @Operation(summary = "Retrieve all Users")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", content = {
//            @Content(schema = @Schema(implementation = UserEntity.class), mediaType = "application/json") }),
//        @ApiResponse(responseCode = "204", description = "There are no users", content = {
//            @Content(schema = @Schema()) }),
//        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
//    @GetMapping("/users")
//    public ResponseEntity<List<UserEntity>> getAllUsers(@RequestParam(required = false) String title) {
//        try {
//            List<UserEntity> users = new ArrayList<>();
//
//            if (title == null)
//                users.addAll(userService.getAllUsers());
//
//            if (users.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

    @Operation(summary = "Create a new User")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json") }),
        @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/create")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        try {
            UserDto createdUserDto = userServiceImpl.post(userDto);
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
