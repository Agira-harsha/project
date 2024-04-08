package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class MatchesResponseDto {
    private String tournamentName;
    private String teamName1;
    private  String  teamName2;
    private LocalDate date;
    private LocalTime time;


}
