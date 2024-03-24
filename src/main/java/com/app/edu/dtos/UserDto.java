package com.app.edu.dtos;

import com.app.edu.utils.AgeCategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private AgeCategoryEnum ageCategoryEnum;
}
