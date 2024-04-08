package com.agira.project.repository;

import com.agira.project.models.MatchWinner;
import com.agira.project.models.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatchWinnerRepository extends JpaRepository<MatchWinner,Long> {


}
