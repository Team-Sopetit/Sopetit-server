package com.soptie.server.persistence.repository.routine.custom;

public interface MemberRoutineCustomRepository {
	void bulkInitAchievement();

	long countByAchieved(boolean isAchieved);
}
