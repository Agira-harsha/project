package com.agira.project.services;

import com.agira.project.models.Team;
import com.agira.project.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;

    public Team saveTeam(Team team){
        teamRepository.save(team);
        return team;
    }
    public Team getTeam(Long id){
        Optional<Team> byId = teamRepository.findById(id);
        return byId.get();
    }

    public Team deleteTeam(Long id){
        Team byId = teamRepository.findById(id).get();
        teamRepository.delete(byId);
        return  byId;
    }

}
