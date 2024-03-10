package com.agira.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tournament-registrations")
public class TournamentRegistrationController {

    private final TournamentRegistrationService tournamentRegistrationService;
    private final TournamentRegistrationMapper tournamentRegistrationMapper;

    @Autowired
    public TournamentRegistrationController(TournamentRegistrationService tournamentRegistrationService, TournamentRegistrationMapper tournamentRegistrationMapper) {
        this.tournamentRegistrationService = tournamentRegistrationService;
        this.tournamentRegistrationMapper = tournamentRegistrationMapper;
    }

    @PostMapping
    public ResponseEntity<TournamentRegistration> registerUserForTournament(@RequestBody TournamentRegistrationRequestDto requestDto) {
        TournamentRegistration registration = tournamentRegistrationMapper.toEntity(requestDto);
        TournamentRegistration savedRegistration = tournamentRegistrationService.registerUserForTournament(registration);
        return new ResponseEntity<>(savedRegistration, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TournamentRegistration>> getAllRegistrations() {
        List<TournamentRegistration> registrations = tournamentRegistrationService.getAllRegistrations();
        return new ResponseEntity<>(registrations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TournamentRegistration> getRegistrationById(@PathVariable Long id) {
        TournamentRegistration registration = tournamentRegistrationService.getRegistrationById(id);
        if (registration != null) {
            return new ResponseEntity<>(registration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        tournamentRegistrationService.deleteRegistration(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Add more controller methods as needed

}
