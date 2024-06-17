package com.soptie.server.history.achievement.entity;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

public enum AchieveType {
	ACHIEVE,
	CANCEL,

	;

	public static AchieveType getType(MemberRoutine memberRoutine) {
		return memberRoutine.isAchieve() ? ACHIEVE : CANCEL;
	}
}
