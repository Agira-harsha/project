package com.agira.project.controllers;

import com.agira.project.Dtos.UserRequestDto;
import com.agira.project.Dtos.UserResponseDto;
import com.agira.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/sigup")
    public UserResponseDto userSinUp(@RequestBody @Valid UserRequestDto userRequestDto){
       return  userService.createAccount(userRequestDto);
    }
    @GetMapping("/login/{id}")
    public ResponseEntity<UserResponseDto>logInUser(@PathVariable long id){
        return userService.getUser(id);
    }
    @PreAuthorize(value = "ROLE_ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto>removeUser(@PathVariable long id)
    {
        return userService.deleteUser(id);
    }
    @GetMapping("/login/users")
    public List<UserResponseDto> getAllUsers(){
        return userService.getAllUsers();
    }
}
