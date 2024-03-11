package com.agira.project.repository;

import com.agira.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserReposiotry extends JpaRepository<User,Long>{
   Optional<User> findByEmail(String email);
}
