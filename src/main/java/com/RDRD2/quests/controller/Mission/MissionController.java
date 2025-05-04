package com.RDRD2.quests.controller.Mission;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.RDRD2.quests.dto.Mission.AssignMissionDTO;
import com.RDRD2.quests.dto.Mission.CreateMissionDTO;
import com.RDRD2.quests.dto.Mission.UpdateMissionDTO;
import com.RDRD2.quests.model.Mission.Mission;
import com.RDRD2.quests.service.Mission.MissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mission")
public class MissionController {
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<Mission> create(@RequestBody @Valid CreateMissionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(missionService.create(dto));
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
    public ResponseEntity<Mission> update(@PathVariable Long id, @RequestBody @Valid UpdateMissionDTO updatedMission) {
        return ResponseEntity.ok(missionService.update(id, updatedMission));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable Long id) {
        missionService.remove(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Mission with ID " + id + " deleted successfully!");

        return ResponseEntity.ok(response);
    }

    @PostMapping("/assign-player")
    public ResponseEntity<Mission> assignMission(@RequestBody AssignMissionDTO assignMissionDTO) {
        return ResponseEntity
                .ok(missionService.assignMission(assignMissionDTO.getPlayerId(), assignMissionDTO.getMissionId()));
    }

    @PatchMapping("/{missionId}/complete")
    public ResponseEntity<Void> completeMission(@PathVariable Long missionId) {
        missionService.completeMission(missionId);
        return ResponseEntity.ok().build();
    }
}
