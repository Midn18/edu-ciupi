package com.app.edu.service;

import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.UserDto;
import com.app.edu.entities.UserEntity;
import com.app.edu.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    @Override
    public UserDto getUserById(Integer id) {
        return modelMapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public UserDto createUser(SignUpDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setPassword(bcryptEncoder.encode(userEntity.getPassword()));

        return modelMapper.map(userRepository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto loginUser(String email, String password) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity != null && bcryptEncoder.matches(password, userEntity.getPassword())) {
            return modelMapper.map(userEntity, UserDto.class);
        }
        return null;
    }
}
