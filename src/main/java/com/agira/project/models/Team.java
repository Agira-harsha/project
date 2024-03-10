package com.agira.project.models;

import com.agira.project.validPassword.ValidPassword;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long teamId;
    @Column(unique = true)
    @NotNull
    private String teamName;
    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL )
    @JsonIgnore
    private List<Player> playersList = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

}
