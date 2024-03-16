package com.app.edu.service;

import com.app.edu.dtos.LoginDto;
import com.app.edu.dtos.SignUpDto;
import com.app.edu.dtos.UserDto;
import com.app.edu.entities.UserEntity;
import com.app.edu.exceptions.AppException;
import com.app.edu.repository.UserRepository;
import java.nio.CharBuffer;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public UserDto createUser(SignUpDto signUpDto) {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(signUpDto.getUsername());
        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = modelMapper.map(signUpDto, UserEntity.class);
        user.setPassword(bcryptEncoder.encode(CharBuffer.wrap(signUpDto.getPassword())));

        UserEntity savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto loginUser(LoginDto loginDto) {
        UserEntity userEntity = userRepository.findByUsername(loginDto.getUsername())
            .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        if (bcryptEncoder.matches(CharBuffer.wrap(loginDto.getPassword()), userEntity.getPassword())) {
            return modelMapper.map(userEntity, UserDto.class);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }
}
