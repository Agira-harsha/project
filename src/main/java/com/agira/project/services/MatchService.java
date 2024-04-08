package com.agira.project.services;

import com.agira.project.Dtos.MatchesResponseDto;
import com.agira.project.models.ScheduledMatches;
import com.agira.project.models.Tournament;
import com.agira.project.models.TournamentRegistration;
import com.agira.project.repository.ScheduledMatchesRepository;
import com.agira.project.repository.TournamentRegistrationRepository;
import com.agira.project.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {
    @Autowired
    private ScheduledMatchesRepository scheduledMatchesRepository;
    @Autowired
    private TournamentRepository tournamentRepository;
    @Autowired
    private TournamentRegistrationRepository tournamentRegistrationRepository;
    public List<ScheduledMatches> setMatchesForTournament(Long tournamentId) {
        Tournament tournament =tournamentRepository.findById(tournamentId).get();

        List<TournamentRegistration> registerdDetails = tournamentRegistrationRepository.getRegisterdDetails(tournamentId);
       if(registerdDetails.size()==6){

           List<ScheduledMatches> matches = generateMatchesForTournament(tournament, registerdDetails);
           return scheduledMatchesRepository.saveAll(matches);
       }
       throw new RuntimeException("Tournament is not full");

    }
    private List<ScheduledMatches> generateMatchesForTournament(Tournament tournament, List<TournamentRegistration> registeredTeams) {

        List<ScheduledMatches> matches = new ArrayList<>();

        LocalDate currentDate = tournament.getStartDate();
        LocalTime currentTime = tournament.getStartTime();

        for (int i = 0; i < registeredTeams.size(); i += 2) {
            TournamentRegistration registration1 = registeredTeams.get(i);
            TournamentRegistration registration2 = registeredTeams.get(i + 1);

            ScheduledMatches match = new ScheduledMatches();
            match.setTournament(tournament);
            match.setUser1(registration1.getUser());
            match.setUser2(registration2.getUser());
            match.setDate(currentDate);
            match.setTime(currentTime);
            matches.add(match);

            currentTime = currentTime.plusHours(1);

            currentDate = currentDate.plusDays(1);

        }

        return matches;
    }
    public List<MatchesResponseDto> getMatches(Long id) {
        List<ScheduledMatches> scheduledMatches = scheduledMatchesRepository.scheduledMatches(id);
        List<MatchesResponseDto> schedule = new ArrayList<>();

        for (ScheduledMatches matches : scheduledMatches) {
            MatchesResponseDto matchesResponseDto = new MatchesResponseDto();

            matchesResponseDto.setTournamentName(matches.getTournament().getTournamentName());
            matchesResponseDto.setTeamName1(matches.getUser1().getTeam().getTeamName());
            matchesResponseDto.setTeamName2(matches.getUser2().getTeam().getTeamName());
            matchesResponseDto.setDate(matches.getDate());
            matchesResponseDto.setTime(matches.getTime());

            schedule.add(matchesResponseDto);
        }

        return schedule;
    }
    public  void tournamentFull(){
        List<Tournament> all = tournamentRepository.findAll();

        List<Long> collect = all.stream().map(Tournament::getTournamentId).collect(Collectors.toList());
        

    }
}

