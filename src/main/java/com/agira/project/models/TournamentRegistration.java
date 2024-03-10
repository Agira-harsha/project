package com.agira.project.models;

public class TournamentRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    private LocalDateTime registrationDateTime;
}