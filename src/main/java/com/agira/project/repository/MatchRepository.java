package com.agira.project.repository;

import com.agira.project.models.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Matches,Long> {
}
