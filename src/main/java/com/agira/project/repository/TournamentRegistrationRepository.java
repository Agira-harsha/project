package com.agira.project.repository;

import com.agira.project.models.Team;
import com.agira.project.models.Tournament;
import com.agira.project.models.TournamentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistration, Long> {
    List<Team> findTeamByTournament(Tournament tournament);
    @Query("SELECT COUNT(u) FROM TournamentRegistration u WHERE u.user.id = :userId AND u.tournament.id = :tournamentId")
    int isFieldExists(@Param("userId") Long userId ,@Param("tournamentId") Long tournamentId);
    @Query("SELECT u  FROM TournamentRegistration  u where tournament_id=:id")
    List<TournamentRegistration> getRegisterdDetails(@Param("id") Long id);

}
