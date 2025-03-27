package com.RDRD2.quests.service.Mission;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.RDRD2.quests.model.mission.Mission;
import com.RDRD2.quests.model.player.Player;
import com.RDRD2.quests.repository.mission.MissionRepository;
import com.RDRD2.quests.repository.player.PlayerRepository;

import jakarta.transaction.Transactional;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class MissionService {
  private final MissionRepository missionRepository;
    private final PlayerRepository playerRepository;

    public MissionService(MissionRepository missionRepository, PlayerRepository playerRepository) {
        this.missionRepository = missionRepository;
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Mission create(Mission mission) {
        return missionRepository.save(mission);
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
    public Mission update(Long id, Mission updatedMission) {
        return missionRepository.findById(id).map(mission -> {
            mission.setName(updatedMission.getName());
            mission.setDescription(updatedMission.getDescription());
            mission.setReputationReward(updatedMission.getReputationReward());
            mission.setMoneyReward(updatedMission.getMoneyReward());
            mission.setCompleted(updatedMission.isCompleted());
            return missionRepository.save(mission);
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mission with ID " + id + " not found!"));
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
        Player player = playerRepository.findById(playerId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player with ID " + playerId + " not found!"));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Mission with ID " + missionId + " not found!"));

        mission.setPlayer(player);
        return missionRepository.save(mission);
    }
}
