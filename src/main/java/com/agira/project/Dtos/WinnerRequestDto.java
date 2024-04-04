package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WinnerRequestDto {
    private  long tournamentID;
    private long userId1;
    private  long userId2;
    private int team1Score;
    private int team2Score;
}
