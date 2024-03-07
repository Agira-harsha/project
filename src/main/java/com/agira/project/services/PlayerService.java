package com.agira.project.services;

import com.agira.project.models.Player;
import com.agira.project.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    public Player  savePlayer(Player player){
       return  playerRepository.save(player);
    }
    public Player getPlayer(Long id){
        return playerRepository.findById(id).get();
    }

    public Player deletePlayer(Long id){
        Player player = playerRepository.findById(id).get();
        playerRepository.delete(player);
        return  player;

    }
}
