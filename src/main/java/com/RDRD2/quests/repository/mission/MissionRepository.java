package com.RDRD2.quests.repository.mission;

import com.RDRD2.quests.model.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long>{
}
