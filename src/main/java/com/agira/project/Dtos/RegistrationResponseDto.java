package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationResponseDto {
    private Long registerId;
    private String admin;
    private  String tournamentName;
    private double winPrice;
    private Long userId;
    private Long tourId;

}
