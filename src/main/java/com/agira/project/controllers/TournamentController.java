package com.agira.project.controllers;

import com.agira.project.models.Tournament;
import com.agira.project.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    TournamentService tournamentService;


    @PostMapping("/create")
    public ResponseEntity<Tournament> saveTournament(@RequestBody Tournament tournament){
         return ResponseEntity.ok(tournamentService.saveTournament(tournament));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseEntity<Optional<Tournament>>> deleteTournament(@PathVariable Long id){
       return ResponseEntity.ok(tournamentService.deleteTournament(id));
    }
}
