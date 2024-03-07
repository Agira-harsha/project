package com.agira.project.controllers;

import com.agira.project.models.Player;
import com.agira.project.models.Team;
import com.agira.project.services.PlayerService;
import com.agira.project.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/team/player")
public class PlayerController {
    @Autowired
    PlayerService playerService;
    @Autowired
    TeamService teamService;
    @PostMapping("/save/{id}")
    public ResponseEntity<Player> savePlayer(@RequestBody @Valid Player player, @PathVariable Long id) {
        Team team = teamService.getTeam(id);
        player.setTeam(team);
        playerService.savePlayer(player);
        return ResponseEntity.ok(player);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayer(@PathVariable Long id){
        return ResponseEntity.ok(playerService.getPlayer(id));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id){
        return  ResponseEntity.ok(playerService.deletePlayer(id));
    }
}
