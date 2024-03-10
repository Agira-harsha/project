package com.agira.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class User {
    @Id
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    // Constructors, getters, and setters

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    // Getters and setters
    // Omitted for brevity
}
