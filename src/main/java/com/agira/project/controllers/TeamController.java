package com.agira.project.controllers;

import com.agira.project.models.Team;
import com.agira.project.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @PostMapping("/save")
    public void saveTeam(@RequestBody Team team){
        teamService.saveTeam(team);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Team> deleteTeam(@PathVariable Long id){
        Team team = teamService.deleteTeam(id);
        return ResponseEntity.ok(team);
    }
}
