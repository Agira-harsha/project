package com.agira.project.services;

import com.agira.project.Dtos.TeamReponseDto;
import com.agira.project.models.*;
import com.agira.project.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<TeamReponseDto>registerTeams(Long id){

        Tournament tournament = tournamentRepository.findById(id).get();
        List<TournamentRegistration> registrations = tournament.getRegistrations();
        return registrations.stream()
                .map(registration -> {
                    Tournament team = registration.getTournament();
                    User user = registration.getUser();
                    Team team1 = user.getTeam();

                    TeamReponseDto teamReponseDto = new TeamReponseDto();

                  teamReponseDto.setTeamId(team1.getTeamId());
                  teamReponseDto.setTeamName(team1.getTeamName());
                  teamReponseDto.setAdmin(user.getUserName());
                    return teamReponseDto;
                })
                .collect(Collectors.toList());
    }

}
