package com.RDRD2.quests.controller.Mission;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RDRD2.quests.dto.AssignMissionDTO;
import com.RDRD2.quests.model.mission.Mission;
import com.RDRD2.quests.service.Mission.MissionService;


@RestController
@RequestMapping("/mission")
public class MissionController {
  private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<Mission> create(@RequestBody Mission mission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.create(mission));
    }

    @GetMapping
    public ResponseEntity<List<Mission>> findAll() {
        return ResponseEntity.ok(missionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(missionService.findOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Mission> update(@PathVariable Long id, @RequestBody Mission updatedMission) {
        return ResponseEntity.ok(missionService.update(id, updatedMission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remove(@PathVariable Long id) {
        missionService.remove(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/assign-mission")
    public ResponseEntity<Mission> assignMission(@RequestBody AssignMissionDTO assignMissionDTO) {
        return ResponseEntity.ok(missionService.assignMission(assignMissionDTO.getPlayerId(), assignMissionDTO.getMissionId()));
    }

    @PostMapping("/complete/{missionId}")
    public ResponseEntity<Void> completeMission(@PathVariable Long missionId) {
        missionService.completeMission(missionId);
        return ResponseEntity.noContent().build();
    }
}
