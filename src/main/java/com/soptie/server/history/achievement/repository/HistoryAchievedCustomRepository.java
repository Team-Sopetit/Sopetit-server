package com.soptie.server.history.achievement.repository;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

public interface HistoryAchievedCustomRepository {

	boolean isAchievedToday(MemberRoutine routine);
}
