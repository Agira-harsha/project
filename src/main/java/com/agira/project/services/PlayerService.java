package com.agira.project.services;

import com.agira.project.Dtos.PlayerReponseDto;
import com.agira.project.Dtos.PlayerRequestDto;
import com.agira.project.Utility.Mapper;
import com.agira.project.models.Player;
import com.agira.project.models.TournamentRegistration;
import com.agira.project.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    Mapper mapper;
    public PlayerReponseDto savePlayer(PlayerRequestDto playerRequestDto){
        Player player = mapper.playerRequestDtoPlayer(playerRequestDto);
        playerRepository.save(player);
       return  mapper.playerToplayerReponseDto(player);

    }
    public PlayerReponseDto getPlayer(Long id){
        Player player = playerRepository.findById(id).get();
       return mapper.playerToplayerReponseDto(player);

    }

    public PlayerReponseDto deletePlayer(Long id){
        Player player = playerRepository.findById(id).get();
        PlayerReponseDto playerReponseDto = mapper.playerToplayerReponseDto(player);
        playerRepository.delete(player);
        return  playerReponseDto;

    }

    public List<PlayerReponseDto> getAllPlayers() {
        List<Player> all = playerRepository.findAll();
        List<PlayerReponseDto> collect = all.stream().map(player -> mapper.playerToplayerReponseDto(player)).collect(Collectors.toList());
        return  collect;


    }
    public List<PlayerReponseDto> getTeamPlayers(Long id){
        List<Player> players = playerRepository.getPlayers(id);

        List<PlayerReponseDto> collect = players.stream().map(player -> mapper.playerToplayerReponseDto(player)).collect(Collectors.toList());
      return  collect;

    }
}
