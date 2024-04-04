package com.agira.project.repository;

import com.agira.project.Dtos.PlayerReponseDto;
import com.agira.project.models.Player;
import com.agira.project.models.TournamentRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player,Long> {
    @Query("SELECT u  FROM Player  u where team_id=:id")
    List<Player> getPlayers(@Param("id") Long id);
}
