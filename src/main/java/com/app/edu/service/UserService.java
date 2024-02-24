package com.app.edu.service;

import com.app.edu.dtos.UserDto;
import com.app.edu.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {

    public UserDto getById(Integer id);

    public UserDto post(UserDto userDto);
}
