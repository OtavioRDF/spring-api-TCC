package com.RDRD2.quests.service.Player;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.RDRD2.quests.dto.Player.CreatePlayerDTO;
import com.RDRD2.quests.dto.Player.UpdatePlayerDTO;
import com.RDRD2.quests.mapper.PlayerMapper;
import com.RDRD2.quests.model.Player.Player;
import com.RDRD2.quests.repository.Player.PlayerRepository;

import jakarta.transaction.Transactional;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PlayersService {
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayersService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    @Transactional
    public Player create(CreatePlayerDTO player) {
        Player entity = playerMapper.createToEntity(player);
        return playerRepository.save(entity);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!"));
    }

    @Transactional
    public Player update(Long id, UpdatePlayerDTO updatedPlayer) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!"));

        Player updatedEntity = playerMapper.updateToEntity(updatedPlayer, player);

        return playerRepository.save(updatedEntity);
    }

    @Transactional
    public void remove(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!");
        }
        playerRepository.deleteById(id);
    }
}
