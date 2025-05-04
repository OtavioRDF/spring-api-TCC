package com.RDRD2.quests.dto.Player;

import java.util.Map;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class CreatePlayerDTO {
    @NotBlank
    private String name;

    @NotBlank
    private String profile;

    @NotNull
    private Map<String, Integer> skills;

    @PositiveOrZero
    private int money;

    @PositiveOrZero
    private int reputation;
}
