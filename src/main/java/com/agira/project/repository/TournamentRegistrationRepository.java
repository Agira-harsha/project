package com.agira.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournamentRegistrationRepository extends JpaRepository<TournamentRegistration, Long> {
    // You can define custom query methods here if needed
}
