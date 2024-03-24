package com.app.edu.controller;

import com.app.edu.config.UserAuthenticationProvider;
import com.app.edu.dtos.LoginDto;
import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.TokenDto;
import com.app.edu.dtos.UserDto;
import com.app.edu.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "User", description = "User management APIs")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    UserAuthenticationProvider userAuthenticationProvider;

    @Operation(summary = "Create a new User")
    @ApiResponses({
        @ApiResponse(responseCode = "201", content = {
            @Content(schema = @Schema(implementation = UserDto.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpDto signUpDto) {
        UserDto createdUser = userServiceImpl.createUser(signUpDto);
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @Operation(summary = "Login User")
    @ApiResponses({
        @ApiResponse(responseCode = "200", content = {
            @Content(schema = @Schema(implementation = String.class), mediaType = "application/json")}),
        @ApiResponse(responseCode = "404", description = "User not found", content = {
            @Content(schema = @Schema())}),
        @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})})
    @PostMapping("/login")
    public ResponseEntity<TokenDto> loginUser(@RequestBody LoginDto loginDto) {
        UserDto userDto = userServiceImpl.loginUser(loginDto);
        TokenDto token = new TokenDto();
        token.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(token);
    }

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
