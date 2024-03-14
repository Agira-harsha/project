package com.agira.project.repository;

import com.agira.project.models.Team;
import com.agira.project.models.Tournament;
import com.agira.project.models.TournamentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistration, Long> {
    List<Team> findTeamByTournament(Tournament tournament);

}
