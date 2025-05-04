package com.RDRD2.quests.mapper;

import org.springframework.stereotype.Component;

import com.RDRD2.quests.dto.Mission.CreateMissionDTO;
import com.RDRD2.quests.dto.Mission.UpdateMissionDTO;
import com.RDRD2.quests.model.Mission.Mission;

@Component
public class MissionMapper {

    public Mission createToEntity(CreateMissionDTO dto) {
        Mission mission = new Mission();
        mission.setName(dto.getName());
        mission.setDescription(dto.getDescription());
        mission.setReputationReward(dto.getReputationReward());
        mission.setMoneyReward(dto.getMoneyReward());
        mission.setCompleted(false);
        return mission;
    }

    public Mission updateToEntity(UpdateMissionDTO dto, Mission mission) {
        if (dto.getName() != null)
            mission.setName(dto.getName());
        if (dto.getDescription() != null)
            mission.setDescription(dto.getDescription());
        if (dto.getReputationReward() != null)
            mission.setReputationReward(dto.getReputationReward());
        if (dto.getCompleted() != null)
            mission.setCompleted(dto.getCompleted());
        if (dto.getMoneyReward() != null)
            mission.setMoneyReward(dto.getMoneyReward());

        return mission;
    }
}
