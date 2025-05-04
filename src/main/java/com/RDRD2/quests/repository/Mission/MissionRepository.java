package com.RDRD2.quests.repository.Mission;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RDRD2.quests.model.Mission.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>{
}
