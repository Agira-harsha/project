package com.agira.project.Dtos;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PlayerRequestDto {
    @NotNull
    private String playerName;
    @NotNull
    private String role;
    @NotNull
    private long teamId;

}
