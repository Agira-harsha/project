package com.agira.project.repository;

import com.agira.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReposiotry extends JpaRepository<User,Long>{
}
