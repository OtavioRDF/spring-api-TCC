package com.RDRD2.quests.repository.Player;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RDRD2.quests.model.Player.Player;

public interface PlayerRepository extends JpaRepository<Player, Long> {  
}
