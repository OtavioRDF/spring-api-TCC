package com.RDRD2.quests.dto;

public class AssignMissionDTO {
  private Long playerId;
  private Long missionId;

  public Long getPlayerId() {
    return playerId;
  }
  public void setPlayerId(Long playerId) {
    this.playerId = playerId;
  }
  public Long getMissionId() {
    return missionId;
  }
  public void setMissionId(Long missionId) {
    this.missionId = missionId;
  }
}
