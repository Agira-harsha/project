package com.agira.project.Utility;

import com.agira.project.Dtos.UserRequestDto;
import com.agira.project.Dtos.UserResponseDto;
import com.agira.project.models.User;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserMapper {
    public User userRequestToUser(UserRequestDto userRequestDto){
        User savedUser=new User();
        savedUser.setUserName(userRequestDto.getUserName());
        savedUser.setPassword(userRequestDto.getPassword());
        savedUser.setEmail(userRequestDto.getEmail());
        return savedUser;

    }
    public UserResponseDto userToResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUserName(user.getEmail());
        userResponseDto.setUserId(user.getUserId());
        return userResponseDto;
    }
}
