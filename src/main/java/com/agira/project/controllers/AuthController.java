package com.agira.project.controllers;

import com.agira.project.Dtos.UserLoginDto;
import com.agira.project.Dtos.UserResponseDto;
import com.agira.project.appconfig.JwtTokenProvider;
import com.agira.project.models.User;
import com.agira.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/user")
public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
   public ResponseEntity<UserResponseDto>loginUser(@RequestBody @Valid UserLoginDto loginDto){
       Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

       SecurityContextHolder.getContext().setAuthentication(authenticate);
       String token=jwtTokenProvider.generateToken(authenticate);
        UserResponseDto userByEmail = userService.getUserByEmail(loginDto.getEmail());
        userByEmail.setToken(token);
        return  new ResponseEntity<>(userByEmail,HttpStatus.OK);
   }
}

