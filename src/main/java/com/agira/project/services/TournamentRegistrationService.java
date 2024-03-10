package com.agira.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentRegistrationService {

    private final TournamentRegistrationRepository tournamentRegistrationRepository;

    @Autowired
    public TournamentRegistrationService(TournamentRegistrationRepository tournamentRegistrationRepository) {
        this.tournamentRegistrationRepository = tournamentRegistrationRepository;
    }

    public TournamentRegistration registerUserForTournament(User user, Tournament tournament) {
        TournamentRegistration registration = new TournamentRegistration();
        registration.setUser(user);
        registration.setTournament(tournament);
        registration.setRegistrationDateTime(LocalDateTime.now());
        return tournamentRegistrationRepository.save(registration);
    }

    public List<TournamentRegistration> getAllRegistrations() {
        return tournamentRegistrationRepository.findAll();
    }

    public Optional<TournamentRegistration> getRegistrationById(Long id) {
        return tournamentRegistrationRepository.findById(id);
    }

    public void deleteRegistration(Long id) {
        tournamentRegistrationRepository.deleteById(id);
    }

    // Add more methods as needed

}
