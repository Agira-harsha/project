package com.agira.project.models;

import com.agira.project.validPassword.ValidPassword;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratorType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String userName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    private String password;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Team team;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<TournamentRegistration> tournamentRegistrations;
    @ManyToMany(cascade =CascadeType.ALL)
    private List<Role> roleList= new ArrayList<>();
}
