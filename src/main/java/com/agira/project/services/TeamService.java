package com.agira.project.services;

import com.agira.project.Dtos.PlayerReponseDto;
import com.agira.project.Dtos.TeamReponseDto;
import com.agira.project.Dtos.TeamRequestDto;
import com.agira.project.Utility.Mapper;
import com.agira.project.models.Player;
import com.agira.project.models.Team;
import com.agira.project.models.User;
import com.agira.project.repository.TeamRepository;
import com.agira.project.repository.UserReposiotry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    UserReposiotry userReposiotry;
    @Autowired
    Mapper mapper;

    public ResponseEntity<TeamReponseDto> saveTeam(TeamRequestDto teamRequestDto) {
        Team team = mapper.teamRequestDtoToTeam(teamRequestDto);
        User user = userReposiotry.findById(teamRequestDto.getUserId()).get();
        team.setUser(user);
        Team save = teamRepository.save(team);
        TeamReponseDto teamReponseDto = mapper.teamToTeamReponseDto(team);
        teamReponseDto.setAdmin(user.getUserName());
        teamReponseDto.setTeamName(team.getTeamName());
        teamReponseDto.setTeamId(save.getTeamId());
        return ResponseEntity.ok(teamReponseDto);
    }

    public TeamReponseDto getTeam(long id) {
        Team team = teamRepository.findById(id).get();
        User user = team.getUser();
        User byId = userReposiotry.findById(user.getUserId()).get();
        TeamReponseDto teamReponseDto = mapper.teamToTeamReponseDto(team);
        teamReponseDto.setAdmin(byId.getUserName());
        teamReponseDto.setAdmin(user.getUserName());
        teamReponseDto.setTeamName(team.getTeamName());
        teamReponseDto.setTeamId(team.getTeamId());
     return teamReponseDto;

    }

    public TeamReponseDto deleteTeam(long id) {
        TeamReponseDto team = getTeam(id);
        teamRepository.deleteById(id);
        return team;
    }

    public List<TeamReponseDto> getAllTeams() {
        List<Team> all = teamRepository.findAll();
        List<TeamReponseDto> collect = all.stream().map(team -> mapper.teamToTeamReponseDto(team)).collect(Collectors.toList());

        return collect;
    }
    public List<Player> getPlayersByTeamId(Long id) {
        Team team = teamRepository.findById(id).orElse(null);
        if (team != null) {
            return team.getPlayersList();
        } else {

            return null;
        }
    }

}
