package com.agira.project.repository;

import com.agira.project.models.Player;
import com.agira.project.models.ScheduledMatches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduledMatchesRepository extends JpaRepository<ScheduledMatches ,Long> {
    @Query("SELECT u  FROM ScheduledMatches  u where tournament_id=:id")
    List<ScheduledMatches> scheduledMatches(@Param("id") Long id);
}
