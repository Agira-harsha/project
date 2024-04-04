package com.agira.project.controllers;

import com.agira.project.Dtos.TeamReponseDto;
import com.agira.project.Dtos.TeamRequestDto;
import com.agira.project.models.Player;
import com.agira.project.models.Team;
import com.agira.project.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;

    @PostMapping("/")
    public ResponseEntity<TeamReponseDto> saveTeam(@RequestBody @Valid TeamRequestDto teamRequestDto) {
        return teamService.saveTeam(teamRequestDto);
    }

    @GetMapping("/login/{id}")
    public ResponseEntity<TeamReponseDto> getTeam(@PathVariable long id) {
        TeamReponseDto team = teamService.getTeam(id);
        return ResponseEntity.ok(team);
    }

    @DeleteMapping("/signout/{id}")
    public ResponseEntity<TeamReponseDto> deleteTeam(@PathVariable long id) {
        TeamReponseDto team = teamService.deleteTeam(id);
        return ResponseEntity.ok(team);
    }
    @GetMapping
    public ResponseEntity<List<TeamReponseDto>> getAllTeams() {
        List<TeamReponseDto> teams = teamService.getAllTeams();
        return  ResponseEntity.ok(teams);
    }
    @GetMapping("/players/{id}")
    public ResponseEntity<List<Player>>getAllPlayerAsSameTeam(@PathVariable Long id){
        List<Player> players = teamService.getPlayersByTeamId(id);
        return ResponseEntity.ok(players);
    }
}
