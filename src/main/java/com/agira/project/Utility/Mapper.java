package com.agira.project.Utility;

import com.agira.project.Dtos.*;
import com.agira.project.models.Player;
import com.agira.project.models.Team;
import com.agira.project.models.TournamentRegistration;
import com.agira.project.models.User;
import com.agira.project.repository.TeamRepository;
import com.agira.project.repository.TournamentRepository;
import com.agira.project.repository.UserReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class Mapper {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    TournamentRepository tournamentRepository;
    @Autowired
    UserReposiotry userReposiotry;


    public User userRequestToUser(UserRequestDto userRequestDto) {
        User savedUser = new User();
        savedUser.setUserName(userRequestDto.getUserName());
        savedUser.setPassword(userRequestDto.getPassword());
        savedUser.setEmail(userRequestDto.getEmail());
        return savedUser;

    }

    public UserResponseDto userToResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setUserName(user.getUserName());
        userResponseDto.setUserId(user.getUserId());
        return userResponseDto;
    }

    public Team teamRequestDtoToTeam(TeamRequestDto teamRequestDto) {
        Team team = new Team();
        team.setTeamName(teamRequestDto.getTeamName());
        return team;
    }

    public TeamReponseDto teamToTeamReponseDto(Team team) {
        TeamReponseDto teamReponseDto = new TeamReponseDto();
        teamReponseDto.setTeamName(team.getTeamName());
        teamReponseDto.setTeamId(team.getTeamId());
        teamReponseDto.setAdmin(team.getUser().getUserName());
        return teamReponseDto;

    }

    public Player playerRequestDtoPlayer(PlayerRequestDto playerRequestDto) {
        Player player = new Player();
        long teamId = playerRequestDto.getTeamId();
        Team team = teamRepository.findById(teamId).get();
        if (!team.isFull()) {
            team.addPlayer(player);
            player.setTeam(team);
        } else {
            throw new IllegalStateException("Team is already full");
        }

        player.setPlayerName(playerRequestDto.getPlayerName());
        player.setRole(playerRequestDto.getRole());
        return player;
    }

    public PlayerReponseDto playerToplayerReponseDto(Player player) {
        PlayerReponseDto playerReponseDto = new PlayerReponseDto();
        playerReponseDto.setPlayerName(player.getPlayerName());
        playerReponseDto.setRole(player.getRole());
        playerReponseDto.setTeam(player.getTeam().getTeamName());
        playerReponseDto.setPlayerId(player.getPlayerId());
        return playerReponseDto;
    }

    public TournamentRegistration registrationDtoToEnity(TournamentRegistrationRequestDto requestDto) {
        TournamentRegistration registration = new TournamentRegistration();
        registration.setTournament(tournamentRepository.findById(requestDto.getTournamentId()).get());
        registration.setUser(userReposiotry.findById(requestDto.getUserId()).get());
        registration.setRegistrationDateTime(LocalDateTime.now());
        return registration;
    }

    public RegistrationResponseDto entityToResponse(TournamentRegistration registration) {
        RegistrationResponseDto responseDto = new RegistrationResponseDto();
        responseDto.setRegisterId(registration.getRegisterId());
        responseDto.setAdmin(registration.getUser().getUserName());
        responseDto.setTournamentName(registration.getTournament().getTournamentName());
        responseDto.setWinPrice(registration.getTournament().getPrice());
        responseDto.setUserId(registration.getUser().getUserId());
        responseDto.setTourId(registration.getTournament().getTournamentId());
        return responseDto;
    }

}
