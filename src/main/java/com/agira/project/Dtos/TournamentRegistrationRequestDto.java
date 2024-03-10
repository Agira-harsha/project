package com.agira.project.Dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TournamentRegistrationRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long tournamentId;

}
