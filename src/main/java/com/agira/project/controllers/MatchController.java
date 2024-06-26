package com.agira.project.controllers;

import com.agira.project.Dtos.MatchWinnerDto;
import com.agira.project.Dtos.MatchesResponseDto;
import com.agira.project.Dtos.WinnerRequestDto;
import com.agira.project.models.ScheduledMatches;
import com.agira.project.services.MatchService;
import com.agira.project.services.WinnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    WinnerService winnerService;
    @Autowired
    MatchService matchService;

    @PostMapping("/winner")
    public ResponseEntity<MatchWinnerDto>tournamentWinner(@RequestBody WinnerRequestDto winnerRequestDto){
        MatchWinnerDto winner = winnerService.getWinner(winnerRequestDto);
        return  ResponseEntity.ok(winner);
    }
    @GetMapping()
    public ResponseEntity<List<MatchWinnerDto>> allWinner(){
        return  ResponseEntity.ok(winnerService.getAllWinners());
    }
    @GetMapping("/{id}")
    public List<ScheduledMatches> createScheduledMatches(@PathVariable Long id){
        return matchService.setMatchesForTournament( id);
    }
    @GetMapping("/scheduled/{id}")
    public List<MatchesResponseDto> scheduledWitTournament(@PathVariable Long id){
        return matchService.getMatches(id);
    }
}
