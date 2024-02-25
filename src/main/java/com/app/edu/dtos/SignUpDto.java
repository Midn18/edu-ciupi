package com.app.edu.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Integer age;
}
