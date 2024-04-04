package com.agira.project.models;

import com.agira.project.repository.UserReposiotry;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MatchWinner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    @ManyToOne
    @JoinColumn(name = "tournament_id",unique = true )
    private Tournament tournament;
    @ManyToOne
    @JoinColumn(name = "user1_id")
    private User user1;
    @ManyToOne
    @JoinColumn(name = "user2_id")
    private User user2;
    private int team1Score;
    private int team2Score;
    private long winTeamId;

}
