package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchWinnerResponse {
    private  Long teamId;
    private String  admin;
    private double prize;
    private String status="Won";
}
