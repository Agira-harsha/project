package com.agira.project.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
@Data
@Entity
public class TournamentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registerId;

    @ManyToOne
    @JoinColumn(name = "user_id",unique = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @Column(name = "registration_date")
    private LocalDateTime registrationDateTime;
}