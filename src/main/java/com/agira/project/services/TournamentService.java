package com.agira.project.services;

import com.agira.project.models.Tournament;
import com.agira.project.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;
    public Tournament saveTournament(Tournament tournament){
      return tournamentRepository.save(tournament);
    }
    public ResponseEntity<Optional<Tournament>> deleteTournament(Long id){
        Optional<Tournament> byId = tournamentRepository.findById(id);
        tournamentRepository.delete(byId.get());
        return ResponseEntity.ok(byId);
    }
}
