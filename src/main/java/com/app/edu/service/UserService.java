package com.app.edu.service;

import com.app.edu.dtos.LoginDto;
import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.UserDto;

public interface UserService {

    UserDto getUserById(Integer id);

    UserDto createUser(SignUpDto signUpDto);

    UserDto loginUser(LoginDto loginDto);
}
