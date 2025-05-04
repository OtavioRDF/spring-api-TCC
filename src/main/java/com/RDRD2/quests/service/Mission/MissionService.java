package com.RDRD2.quests.service.Mission;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.RDRD2.quests.dto.Mission.CreateMissionDTO;
import com.RDRD2.quests.dto.Mission.UpdateMissionDTO;
import com.RDRD2.quests.mapper.MissionMapper;
import com.RDRD2.quests.model.Mission.Mission;
import com.RDRD2.quests.model.Player.Player;
import com.RDRD2.quests.repository.Mission.MissionRepository;
import com.RDRD2.quests.repository.Player.PlayerRepository;

import jakarta.transaction.Transactional;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final PlayerRepository playerRepository;
    private final MissionMapper missionMapper;

    public MissionService(MissionRepository missionRepository, PlayerRepository playerRepository,
            MissionMapper missionMapper) {
        this.missionRepository = missionRepository;
        this.playerRepository = playerRepository;
        this.missionMapper = missionMapper;
    }

    @Transactional
    public Mission create(CreateMissionDTO mission) {
        Mission entity = missionMapper.createToEntity(mission);
        return missionRepository.save(entity);
    }

    @Transactional
    public List<Mission> findAll() {
        return missionRepository.findAll();
    }

    public Mission findOne(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mission with ID " + id + " not found!"));
    }

    @Transactional
    public Mission update(Long id, UpdateMissionDTO updatedMission) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mission with ID " + id + " not found!"));

        Mission updatedEntity = missionMapper.updateToEntity(updatedMission, mission);

        return missionRepository.save(updatedEntity);
    }

    @Transactional
    public void remove(Long id) {
        if (!missionRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Mission with ID " + id + " not found!");
        }
        missionRepository.deleteById(id);
    }

    @Transactional
    public void completeMission(Long missionId) {
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mission not found"));

        if (mission.isCompleted()) {
            throw new ResponseStatusException(NOT_FOUND, "Mission already completed");
        }

        // Atualiza o status da missão
        mission.setCompleted(true);
        missionRepository.save(mission);

        // Atualiza o jogador
        Player player = mission.getPlayer();
        if (player != null) {
            player.setReputation(player.getReputation() + mission.getReputationReward());
            player.setMoney(player.getMoney() + mission.getMoneyReward());
            playerRepository.save(player);
        }
    }

    @Transactional
    public Mission assignMission(Long playerId, Long missionId) {
        try {
            Player player = playerRepository.findById(playerId)
                    .orElseThrow(
                            () -> new ResponseStatusException(NOT_FOUND, "Player with ID " + playerId + " not found!"));

            Mission mission = missionRepository.findById(missionId)
                    .orElseThrow(
                            () -> new ResponseStatusException(NOT_FOUND,
                                    "Mission with ID " + missionId + " not found!"));

            mission.setPlayer(player);

            if (mission.isCompleted()) {
                player.setReputation(mission.getReputationReward());
                player.setMoney(mission.getMoneyReward());
                playerRepository.save(player);
            }
            return missionRepository.save(mission);

        } catch (Exception error) {
            throw new ResponseStatusException(NOT_FOUND, "Error assigning mission: " + error.getMessage());
        }
    }
}
