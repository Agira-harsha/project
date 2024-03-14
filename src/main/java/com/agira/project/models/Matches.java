package com.agira.project.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tournament_id",unique = true    )
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    private int team1Score;
    private int team2Score;


}
