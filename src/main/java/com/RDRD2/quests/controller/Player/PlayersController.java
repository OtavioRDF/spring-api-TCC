package com.RDRD2.quests.controller.Player;

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

import com.RDRD2.quests.dto.Player.CreatePlayerDTO;
import com.RDRD2.quests.dto.Player.UpdatePlayerDTO;
import com.RDRD2.quests.model.Player.Player;
import com.RDRD2.quests.service.Player.PlayersService;

@RestController
@RequestMapping("/players")
public class PlayersController {
    private final PlayersService playersService;

    public PlayersController(PlayersService playersService) {
        this.playersService = playersService;
    }

    @PostMapping
    public ResponseEntity<Player> create(@RequestBody CreatePlayerDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playersService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Player>> findAll() {
        return ResponseEntity.ok(playersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(playersService.findOne(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Player> update(@PathVariable Long id, @RequestBody UpdatePlayerDTO updatedPlayer) {
        return ResponseEntity.ok(playersService.update(id, updatedPlayer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable Long id) {
        playersService.remove(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Player with ID " + id + " deleted successfully!");

        return ResponseEntity.ok(response);
    }
}
