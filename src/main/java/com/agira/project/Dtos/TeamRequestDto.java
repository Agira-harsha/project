package com.agira.project.Dtos;

import com.agira.project.models.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class TeamRequestDto {
    @Column(unique = true)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "name must be letters")
    private String teamName;
    @NotNull
    private long userId;


}
