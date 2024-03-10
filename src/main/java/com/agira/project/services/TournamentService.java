package com.agira.project.services;

import com.agira.project.models.Tournament;
import com.agira.project.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    TournamentRepository tournamentRepository;
    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament tournament) {
        if (tournamentRepository.existsById(id)) {
            tournament.setTournamentId(id);
            return tournamentRepository.save(tournament);
        }
        return null;
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
