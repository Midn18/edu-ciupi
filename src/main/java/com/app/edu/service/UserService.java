package com.app.edu.service;

import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.UserDto;

public interface UserService {

    public UserDto getUserById(Integer id);

    public UserDto createUser(SignUpDto signUpDto);

    public UserDto loginUser(String email, String password);
}
