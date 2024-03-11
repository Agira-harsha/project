package com.agira.project.Dtos;

import com.agira.project.validPassword.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {
    @Email
    private  String email;
    @ValidPassword
    private String password;

}
