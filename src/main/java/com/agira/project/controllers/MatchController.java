package com.agira.project.controllers;

import com.agira.project.Dtos.MatchWinnerDto;
import com.agira.project.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    MatchService matchService;
    @PostMapping("/tournament-conduct/{id}")
    public ResponseEntity<MatchWinnerDto> tournamentStarts(@PathVariable long id){
        MatchWinnerDto teamReponseDto = matchService.conductMatchAndDeclareWinner(id);
        return ResponseEntity.ok(teamReponseDto);
    }


}
