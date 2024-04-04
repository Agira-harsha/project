package com.agira.project.services;

import com.agira.project.Dtos.RegistrationResponseDto;
import com.agira.project.Dtos.TournamentRegistrationRequestDto;
import com.agira.project.ExceptionController.UserAlreadyExistsException;
import com.agira.project.Utility.Mapper;
import com.agira.project.models.Tournament;
import com.agira.project.models.TournamentRegistration;
import com.agira.project.repository.TournamentRegistrationRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TournamentRegistrationService {
    @Autowired
    private TournamentRegistrationRepository tournamentRegistrationRepository;
    @Autowired
    Mapper mapper;
    @Autowired
    JavaMailSender javaMailSender;

    @SneakyThrows
    public RegistrationResponseDto userTournamentRegistration(TournamentRegistrationRequestDto requestDto) {
        TournamentRegistration registration = mapper.registrationDtoToEnity(requestDto);
        if (tournamentRegistrationRepository.isFieldExists(registration.getUser().getUserId(), registration.getTournament().getTournamentId()) == 0) {
            Tournament tournament = registration.getTournament();
            if (!tournament.isFull()) {
                tournament.addRegistration(registration);
                tournamentRegistrationRepository.save(registration);
                SimpleMailMessage simpleMailMessage = getSimpleMailMessage(registration);
                javaMailSender.send(simpleMailMessage);
                return mapper.entityToResponse(registration);
            } else {
                throw new IllegalStateException("Tournament is already full");
            }
        } else {
            throw new UserAlreadyExistsException("user Already Registered");
        }
    }

    private static SimpleMailMessage getSimpleMailMessage(TournamentRegistration registration) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(registration.getUser().getEmail());
        simpleMailMessage.setSubject("Tournament Registration Successful.");
        String mailTemplate = String.format("Dear %s,\n\nCongratulations! You have successfully registered for the %s tournament.\n\nPrize Money: %s\n\nThank you for participating!\n\nBest regards", registration.getUser().getUserName(), registration.getTournament().getTournamentName(), registration.getTournament().getPrice());
        simpleMailMessage.setText(mailTemplate);
        return simpleMailMessage;
    }


    public RegistrationResponseDto getRegisterDetailsByID(Long id) {
        TournamentRegistration registration = tournamentRegistrationRepository.findById(id).get();
        return mapper.entityToResponse(registration);
    }

    public List<RegistrationResponseDto> getHistory() {
        List<TournamentRegistration> all = tournamentRegistrationRepository.findAll();
        return all.stream().map(registration -> mapper.entityToResponse(registration)).collect(Collectors.toList());

    }

    public List<RegistrationResponseDto> registerdTeams(Long id) {
        List<TournamentRegistration> registerdDetails = tournamentRegistrationRepository.getRegisterdDetails(id);
        List<RegistrationResponseDto> collect = registerdDetails.stream().map(registration -> mapper.entityToResponse(registration)).collect(Collectors.toList());
        return collect;
    }

}
