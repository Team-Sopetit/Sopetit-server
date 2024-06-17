package com.soptie.server.history.achievement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.history.achievement.entity.HistoryAchieved;
import com.soptie.server.member.entity.Member;

public interface HistoryAchievedRepository extends JpaRepository<HistoryAchieved, Long>, HistoryAchievedCustomRepository {
	void deleteByMember(Member member);
}
