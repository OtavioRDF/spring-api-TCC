package com.RDRD2.quests.repository.player;

import com.RDRD2.quests.model.player.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {  
}
