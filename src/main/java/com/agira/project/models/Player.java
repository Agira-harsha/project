package com.agira.project.models;

import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@NoArgsConstructor
@Data
@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;
    @NotNull
    private String playerName;
    @NotNull
    private String role;
    @ManyToOne
    @JoinColumn(name ="team_id")
    private Team team;
}
