package com.RDRD2.quests.service.Player;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.RDRD2.quests.model.player.Player;
import com.RDRD2.quests.repository.player.PlayerRepository;

import jakarta.transaction.Transactional;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class PlayersService {
      private final PlayerRepository playerRepository;

    public PlayersService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    public Player findOne(Long id) {
        return playerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!"));
    }

    @Transactional
    public Player update(Long id, Player updatedPlayer) {
        return playerRepository.findById(id).map(player -> {
            player.setName(updatedPlayer.getName());
            player.setProfile(updatedPlayer.getProfile());
            player.setReputation(updatedPlayer.getReputation());
            player.setMoney(updatedPlayer.getMoney());
            player.setSkills(updatedPlayer.getSkills());
            return playerRepository.save(player);
        }).orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!"));
    }

    @Transactional
    public void remove(Long id) {
        if (!playerRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Player with ID " + id + " not found!");
        }
        playerRepository.deleteById(id);
    }
}
