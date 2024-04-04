package com.agira.project.controllers;

import com.agira.project.Dtos.PlayerReponseDto;
import com.agira.project.Dtos.PlayerRequestDto;
import com.agira.project.services.PlayerService;
import com.agira.project.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;

    @PostMapping("/")
    public ResponseEntity<PlayerReponseDto> savePlayer(@RequestBody @Valid PlayerRequestDto playerRequestDto) {
        PlayerReponseDto playerReponseDto = playerService.savePlayer(playerRequestDto);
        return ResponseEntity.ok(playerReponseDto);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerReponseDto> getPlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayer(id));
    }

    @DeleteMapping("/signout/{id}")
    public ResponseEntity<PlayerReponseDto> deletePlayer(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.deletePlayer(id));
    }

    @GetMapping("/info")
    public ResponseEntity<List<PlayerReponseDto>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping("/team/players/{id}")
    public ResponseEntity<List<PlayerReponseDto>>getTeamPlayers(@PathVariable Long id){
        return ResponseEntity.ok(playerService.getTeamPlayers(id));
    }
}

