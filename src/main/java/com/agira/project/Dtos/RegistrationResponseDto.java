package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationResponseDto {
    private Long registerId;
    private String admin;
//    private  String teamName;
    private  String tournamentName;
    private double winPrice;

}
