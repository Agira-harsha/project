package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private  Long userId;
    private String userName;
    private String email;
    private String token;
}
