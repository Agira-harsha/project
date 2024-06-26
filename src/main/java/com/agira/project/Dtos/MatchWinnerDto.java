package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchWinnerDto {

    private Long tournamentId;
    private String tournamentName;
    private String  admin;
    private String teamName;
    private double prize;
    private String status="Won";
}
