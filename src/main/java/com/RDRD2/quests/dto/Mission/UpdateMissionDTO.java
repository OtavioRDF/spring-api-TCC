package com.RDRD2.quests.dto.Mission;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

@Getter
public class UpdateMissionDTO {
  private String name;

  private String description;
  
  private Integer reputationReward;
  
  @PositiveOrZero
  private Integer moneyReward;
  
  private Boolean completed;
}
