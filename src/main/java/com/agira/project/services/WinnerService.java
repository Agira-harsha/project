package com.agira.project.services;

import com.agira.project.Dtos.MatchWinnerDto;
import com.agira.project.Dtos.WinnerRequestDto;
import com.agira.project.models.MatchWinner;
import com.agira.project.models.Team;
import com.agira.project.models.Tournament;
import com.agira.project.repository.MatchWinnerRepository;
import com.agira.project.repository.TeamRepository;
import com.agira.project.repository.TournamentRepository;
import com.agira.project.repository.UserReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class WinnerService {
    @Autowired
    UserReposiotry userReposiotry;
    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    MatchWinnerRepository matchWinnerRepository;
    @Autowired
    JavaMailSender javaMailSender;

    public MatchWinnerDto getWinner(WinnerRequestDto winnerRequestDto){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        MatchWinner matchWinner=new MatchWinner();
        matchWinner.setTournament(tournamentRepository.findById(winnerRequestDto.getTournamentID()).get());
        matchWinner.setUser1(userReposiotry.findById(winnerRequestDto.getUserId1()).get());
        matchWinner.setUser2(userReposiotry.findById(winnerRequestDto.getUserId2()).get());
        matchWinner.setTeam1Score(winnerRequestDto.getTeam1Score());
        matchWinner.setTeam2Score(winnerRequestDto.getTeam2Score());
        MatchWinnerDto matchWinnerDto=new MatchWinnerDto();
        if(winnerRequestDto.getTeam1Score()> winnerRequestDto.getTeam2Score()){

            matchWinner.setWinTeamId(winnerRequestDto.getUserId1());
            matchWinnerDto.setTournamentId(winnerRequestDto.getTournamentID());
            matchWinnerDto.setTournamentName(tournamentRepository.findById(winnerRequestDto.getTournamentID()).get().getTournamentName());
            matchWinnerDto.setAdmin(userReposiotry.findById(winnerRequestDto.getUserId1()).get().getUserName());
            matchWinnerDto.setTeamName(userReposiotry.findById(winnerRequestDto.getUserId1()).get().getTeam().getTeamName());
            Tournament tournament = tournamentRepository.findById(winnerRequestDto.getTournamentID()).get();
            double price = tournament.getPrice();
            matchWinnerDto.setPrize(price);
            matchWinnerDto.setStatus(" won by "+userReposiotry.findById(winnerRequestDto.getUserId1()).get().getTeam().getTeamName());
            matchWinnerRepository.save(matchWinner);
            simpleMailMessage.setTo(matchWinner.getUser1().getEmail());
            simpleMailMessage.setSubject("Tournament Winner Announcement");
            String mailTemplate = String.format("Dear %s,\n\nCongratulations! You are the winner of the %s tournament.\n\nPrize Money: %s\n\nThank you for participating!\n\nBest regards", matchWinner.getUser1().getUserName(), matchWinner.getTournament().getTournamentName(), matchWinner.getTournament().getPrice());
            simpleMailMessage.setText(mailTemplate);
            javaMailSender.send(simpleMailMessage);
            return matchWinnerDto;
        }else if (winnerRequestDto.getTeam1Score()< winnerRequestDto.getTeam2Score()){
            matchWinner.setWinTeamId(winnerRequestDto.getUserId2());
            matchWinnerDto.setTournamentId(winnerRequestDto.getTournamentID());
            matchWinnerDto.setTournamentName(tournamentRepository.findById(winnerRequestDto.getTournamentID()).get().getTournamentName());
            matchWinnerDto.setAdmin(userReposiotry.findById(winnerRequestDto.getUserId2()).get().getUserName());
            matchWinnerDto.setTeamName(userReposiotry.findById(winnerRequestDto.getUserId2()).get().getTeam().getTeamName());
            Tournament tournament = tournamentRepository.findById(winnerRequestDto.getTournamentID()).get();
            double price = tournament.getPrice();
            matchWinnerDto.setPrize(price);
            matchWinnerDto.setStatus(userReposiotry.findById(winnerRequestDto.getUserId2()).get().getTeam().getTeamName()+" Won ");
            matchWinnerRepository.save(matchWinner);
            simpleMailMessage.setTo(matchWinner.getUser2().getEmail());
            simpleMailMessage.setSubject("Tournament Winner Announcement");
            String mailTemplate = String.format("Dear %s,\n\nCongratulations! You are the winner of the %s tournament.\n\nPrize Money: %s\n\nThank you for participating!\n\nBest regards", matchWinner.getUser2().getUserName(), matchWinner.getTournament().getTournamentName(), matchWinner.getTournament().getPrice());
            simpleMailMessage.setText(mailTemplate);
            javaMailSender.send(simpleMailMessage);
            return matchWinnerDto;

        }
        else{
            matchWinner.setWinTeamId(0);
            return matchWinnerDto;

        }

    }

    public List<MatchWinnerDto> getAllWinners() {
        List<MatchWinner> allMatchWinners = matchWinnerRepository.findAll();
        List<MatchWinnerDto> allMatchWinnerDtos = new ArrayList<>();

        for (MatchWinner matchWinner : allMatchWinners) {
            if (matchWinner == null) {
                continue;
            }

            MatchWinnerDto matchWinnerDto = new MatchWinnerDto();
            matchWinnerDto.setTournamentId(matchWinner.getTournament().getTournamentId());
            matchWinnerDto.setTournamentName(matchWinner.getTournament().getTournamentName());

            int team1Score = matchWinner.getTeam1Score();
            int team2Score = matchWinner.getTeam2Score();

            if (team1Score > team2Score) {
                if (matchWinner.getUser1() != null) {
                    matchWinnerDto.setAdmin(matchWinner.getUser1().getUserName());
                    matchWinnerDto.setTeamName(matchWinner.getUser1().getTeam().getTeamName());
                }
            } else if (team1Score < team2Score) {
                if (matchWinner.getUser2() != null) {
                    matchWinnerDto.setAdmin(matchWinner.getUser2().getUserName());
                    matchWinnerDto.setTeamName(matchWinner.getUser2().getTeam().getTeamName());
                }
            } else {

                matchWinnerDto.setAdmin("Tie");
                matchWinnerDto.setTeamName("Tie");
            }

            Tournament tournament = matchWinner.getTournament();
            if (tournament != null) {
                double price = tournament.getPrice();
                matchWinnerDto.setPrize(price);
            }

            String status;
            if (team1Score > team2Score) {
                if (matchWinner.getUser1() != null && matchWinner.getUser1().getTeam().getTeamName() != null) {
                    int score=team1Score-team2Score;
                    status = matchWinner.getUser1().getTeam().getTeamName()+" won by " +score +" runs .";
                } else {
                    status = " won (details not available)";
                }
            } else {
                if (matchWinner.getUser2() != null && matchWinner.getUser2().getTeam() != null && matchWinner.getUser2().getTeam().getTeamName() != null) {
                    int score=team2Score-team1Score;

                    status = matchWinner.getUser2().getTeam().getTeamName()+" won by " + score+" runs .";
                } else {
                    status = " won (details not available)";
                }
            }

            matchWinnerDto.setStatus(status);

            allMatchWinnerDtos.add(matchWinnerDto);
        }

        return allMatchWinnerDtos;


    }
}
