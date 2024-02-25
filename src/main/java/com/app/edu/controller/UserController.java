package com.app.edu.controller;

import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.UserDto;
import com.app.edu.repository.UserRepository;
import com.app.edu.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User management APIs")
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @Autowired
    private UserRepository userRepository;
//
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Operation(summary = "Create a new User")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpDto signUpDto) {
        try {
            // checking for username exists in a database
            if (userRepository.existsByUsername(signUpDto.getUsername())) {
                return new ResponseEntity<>("Username is already exist!", HttpStatus.BAD_REQUEST);
            }
            // checking for email exists in a database
            if (userRepository.existsByEmail(signUpDto.getEmail())) {
                return new ResponseEntity<>("Email is already exist!", HttpStatus.BAD_REQUEST);
            }
            // creating user object
            UserDto createdUserDto = userServiceImpl.createUser(signUpDto);
            return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @Operation(summary = "Login User")
//    @ApiResponses({
//        @ApiResponse(responseCode = "200", content = {
//            @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json")}),
//        @ApiResponse(responseCode = "404", description = "User not found", content = {
//            @Content(schema = @Schema())}),
//        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> loginUser(@RequestBody LoginDto loginDto) {
//        try {
//            Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            UserDto user = userServiceImpl.loginUser(loginDto.getUsername(), loginDto.getPassword());
//            if (user == null) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }


    @Operation(summary = "Retrieve User by id")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "User not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(required = true) Integer id) {
        try {
            UserDto user = userServiceImpl.getUserById(id);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
