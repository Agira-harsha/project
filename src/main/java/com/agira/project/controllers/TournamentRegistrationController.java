package com.agira.project.controllers;

import com.agira.project.Dtos.RegistrationResponseDto;
import com.agira.project.Dtos.TournamentRegistrationRequestDto;
import com.agira.project.services.TournamentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tournament-registrations")
public class TournamentRegistrationController {

    @Autowired
    private  TournamentRegistrationService registrationService;


    @PostMapping
    public ResponseEntity<RegistrationResponseDto> registerUserForTournament(@RequestBody TournamentRegistrationRequestDto requestDto) {

        RegistrationResponseDto registration = registrationService.userTournamentRegistration(requestDto);
        return ResponseEntity.ok(registration);
    }
    @GetMapping("/success")
    public ResponseEntity<RegistrationResponseDto>getTransaction(Long id){
       return  ResponseEntity.ok(registrationService.getRegisterDetailsByID(id));
    }
    @GetMapping("/histry")
    public ResponseEntity<List<RegistrationResponseDto>> getHistory(){
        List<RegistrationResponseDto> history = registrationService.getHistory();
        return ResponseEntity.ok(history);
    }
    @GetMapping("/{id}")
    public ResponseEntity<List<RegistrationResponseDto>> getRegisterdTeams(@PathVariable Long id){
        return ResponseEntity.ok(registrationService.registerdTeams(id));
    }
}
