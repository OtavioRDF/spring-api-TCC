package com.RDRD2.quests.dto.Player;

import java.util.Map;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class UpdatePlayerDTO {
  private String name;

  private String profile;

  private Map<String, Integer> skills;

  @PositiveOrZero
  private Integer money;

  @PositiveOrZero
  private Integer reputation;
}
