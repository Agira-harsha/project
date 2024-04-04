package com.agira.project.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tournamentId;
    @Column(unique = true)
    @NotNull
    private String tournamentName;
    @NotNull
    private double price;
    @NotNull
    @FutureOrPresent(message = "Start date must be in the future")
    private LocalDate startDate;
    @NotNull
    private LocalTime startTime;
    @OneToMany(mappedBy = "tournament")
    @JsonIgnore
    private List<TournamentRegistration> registrations;
    public boolean isFull() {
        return registrations.size() >= 6;
    }
    public void addRegistration(TournamentRegistration registration) {
        if (!isFull()) {
            registrations.add(registration);
        } else {
            throw new IllegalStateException("Tournament is already full");
        }
    }


}
