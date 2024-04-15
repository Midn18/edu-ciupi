package com.app.edu.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

import com.app.edu.config.UserAuthenticationProvider;
import com.app.edu.dtos.LoginDto;
import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.TokenDto;
import com.app.edu.dtos.UserDto;
import com.app.edu.service.UserServiceImpl;
import com.app.edu.utils.AgeCategoryEnum;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class UserControllerTest {

    @Mock
    private UserServiceImpl userService;
    @Mock
    private UserAuthenticationProvider userAuthenticationProvider;

    @InjectMocks
    private UserController userController;

    private SignUpDto signUpDto;
    private LoginDto loginDto;
    private UserDto userDto;

    @BeforeEach
    void setup() {
        signUpDto = new SignUpDto("John", "Doe", "johndoe", "john@example.com", "password123", AgeCategoryEnum.AGE_3_5);
        loginDto = new LoginDto("johndoe", "password123");
        userDto = new UserDto(1, "johndoe", "John", "Doe", "john@example.com", AgeCategoryEnum.AGE_3_5);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegisterUserSuccess() {
        when(userService.createUser(any(SignUpDto.class))).thenReturn(userDto);
        ResponseEntity<?> response = userController.registerUser(signUpDto);
        assertEquals(CREATED, response.getStatusCode());
        assertEquals(URI.create("/users/1"), response.getHeaders().getLocation());
        assertNotNull(response.getBody());
    }

    @Test
    void testLoginUserSuccess() {
        when(userService.loginUser(any(LoginDto.class))).thenReturn(userDto);
        ResponseEntity<TokenDto> response = userController.loginUser(loginDto);
        assertEquals(OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetUserByIdSuccess() {
        when(userService.getUserById(anyInt())).thenReturn(userDto);
        ResponseEntity<UserDto> response = userController.getUserById(1);
        assertEquals(OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testGetUserByIdNotFound() {
        when(userService.getUserById(anyInt())).thenReturn(null);
        ResponseEntity<UserDto> response = userController.getUserById(1);
        assertEquals(NOT_FOUND, response.getStatusCode());
    }
}
