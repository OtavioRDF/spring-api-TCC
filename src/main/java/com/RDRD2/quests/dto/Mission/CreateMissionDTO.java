package com.RDRD2.quests.dto.Mission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateMissionDTO {
  @NotBlank(message = "Name is mandatory")
  private String name;
  private String description;
  private int reputationReward;
  private int moneyReward;
}
