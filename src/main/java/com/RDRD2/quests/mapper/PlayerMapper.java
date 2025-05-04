package com.RDRD2.quests.mapper;

import org.springframework.stereotype.Component;

import com.RDRD2.quests.dto.Player.CreatePlayerDTO;
import com.RDRD2.quests.dto.Player.UpdatePlayerDTO;
import com.RDRD2.quests.model.Player.Player;

@Component
public class PlayerMapper {

  public Player createToEntity(CreatePlayerDTO dto) {
    Player player = new Player();
    player.setName(dto.getName());
    player.setReputation(dto.getReputation());
    player.setMoney(dto.getMoney());
    player.setProfile(dto.getProfile());
    player.setSkills(dto.getSkills());
    
    return player;
  }

  public Player updateToEntity(UpdatePlayerDTO dto, Player player) {
    if(dto.getName() != null) player.setName(dto.getName());
    
    if(dto.getReputation() != null) player.setReputation(dto.getReputation());
    
    if(dto.getMoney() != null) player.setMoney(dto.getMoney());
    
    if(dto.getProfile() != null) player.setProfile(dto.getProfile());
    
    if(dto.getSkills() != null) {
      player.setSkills(dto.getSkills());
    }

    return player;
  }
}
