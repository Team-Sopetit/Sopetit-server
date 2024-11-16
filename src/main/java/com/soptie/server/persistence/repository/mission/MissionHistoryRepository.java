package com.soptie.server.persistence.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.mission.MissionHistoryEntity;

public interface MissionHistoryRepository extends JpaRepository<MissionHistoryEntity, Long> {
}
