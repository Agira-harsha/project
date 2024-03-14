package com.agira.project.services;

import com.agira.project.Dtos.MatchWinnerDto;
import com.agira.project.Dtos.TeamReponseDto;
import com.agira.project.models.Matches;
import com.agira.project.models.Team;
import com.agira.project.models.Tournament;
import com.agira.project.repository.MatchRepository;
import com.agira.project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    TournamentService tournamentService;
    @Autowired
    TeamRepository teamRepository;




    public TeamReponseDto conductMatchAndReturnWinner(Matches match) {
        int team1Score = generateRandomScore();
        int team2Score = generateRandomScore();
        
        match.setTeam1Score(team1Score);
        match.setTeam2Score(team2Score);
        
        TeamReponseDto winner = determineWinner(match);
        
        matchRepository.save(match);
        
        return winner;
    }

    private TeamReponseDto determineWinner(Matches match) {
        if (match.getTeam1Score() > match.getTeam2Score()) {
            TeamReponseDto winnerTeam1=new TeamReponseDto();
            winnerTeam1.setTeamId(match.getTeam1().getTeamId());
            winnerTeam1.setTeamName(match.getTeam1().getTeamName());
            winnerTeam1.setAdmin(match.getTeam1().getUser().getUserName());
            return winnerTeam1;
        } else if (match.getTeam1Score() < match.getTeam2Score()) {
            TeamReponseDto winnerTeam2=new TeamReponseDto();
            winnerTeam2.setTeamId(match.getTeam2().getTeamId());
            winnerTeam2.setTeamName(match.getTeam2().getTeamName());
            winnerTeam2.setAdmin(match.getTeam2().getUser().getUserName());
            return winnerTeam2;
        } else {
            return null;
        }
    }

    private int generateRandomScore() {
        return (int) (Math.random() * 6);
    }

    private List<TeamReponseDto> getRegisterTeams(Long id){
       return getTwoTeams(tournamentService.registerTeams(id));
    }
    private List<TeamReponseDto>getTwoTeams(List<TeamReponseDto> teamReponseDtos){
        Collections.shuffle(teamReponseDtos);
        TeamReponseDto team1Dto = teamReponseDtos.get(0);
        TeamReponseDto team2Dto = teamReponseDtos.get(1);
        List<TeamReponseDto> selectedTeams = new ArrayList<>();
        selectedTeams.add(team1Dto);
        selectedTeams.add(team2Dto);
        return selectedTeams;
    }
    public MatchWinnerDto conductMatchAndDeclareWinner(Long tournamentId) {
        // Get two teams for the match
        List<TeamReponseDto> teams = getRegisterTeams(tournamentId);
        TeamReponseDto teamReponseDto1 = teams.get(0);
        TeamReponseDto teamReponseDto2 = teams.get(1);
        Team team1 = teamRepository.findById(teamReponseDto1.getTeamId()).get();
        Team team2 = teamRepository.findById(teamReponseDto2.getTeamId()).get();

        if (teams.size() < 2) {
            return null;
        }

        // Create a match
        Matches match = new Matches();
        match.setTeam1(team1);
        match.setTeam2(team2);
        match.setTournament(tournamentService.getTournamentById(tournamentId));
        Tournament tournament = match.getTournament();

        TeamReponseDto winner = conductMatchAndReturnWinner(match);
        MatchWinnerDto winnerDto=new MatchWinnerDto();
        winnerDto.setTeamId(winner.getTeamId());
        winnerDto.setTeamName(winner.getTeamName());
        winnerDto.setAdmin(winner.getAdmin());
        winnerDto.setPrize(tournament.getPrice());
        return winnerDto;
    }

}
