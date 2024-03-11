package com.agira.project.services;

import com.agira.project.Dtos.RegistrationResponseDto;
import com.agira.project.Dtos.TournamentRegistrationRequestDto;
import com.agira.project.Utility.Mapper;
import com.agira.project.models.TournamentRegistration;
import com.agira.project.repository.TournamentRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentRegistrationService {
     @Autowired
    private  TournamentRegistrationRepository tournamentRegistrationRepository;
     @Autowired
    Mapper mapper;

     public RegistrationResponseDto userTournamentRegistration(TournamentRegistrationRequestDto requestDto){
         TournamentRegistration registration = mapper.registrationDtoToEnity(requestDto);
         tournamentRegistrationRepository.save(registration);
         return mapper.entityToResponse(registration);
     }


    public RegistrationResponseDto getRegisterDetailsByID(Long id) {
        TournamentRegistration registration = tournamentRegistrationRepository.findById(id).get();
        return mapper.entityToResponse(registration);
    }
    public List<RegistrationResponseDto> getHistory(){
        List<TournamentRegistration> all = tournamentRegistrationRepository.findAll();
      return  all.stream().map(registration -> mapper.entityToResponse(registration)).collect(Collectors.toList());

    }
}
