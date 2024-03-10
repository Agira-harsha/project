package com.agira.project.Dtos;

import com.agira.project.validPassword.ValidPassword;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserRequestDto {

    @NotNull
    @Pattern(regexp = "^[a-zA-Z]*$", message = "name must be letters")
    private String userName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "Password is required")
    @ValidPassword(message = "enter minimum 6 charecter must be one capital letter and one special and one numeric number")
    private String password;

}
