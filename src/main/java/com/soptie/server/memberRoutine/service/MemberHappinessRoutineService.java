package com.soptie.server.memberRoutine.service;

import com.soptie.server.memberRoutine.dto.*;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

public interface MemberHappinessRoutineService {

	MemberHappinessRoutineResponse createMemberHappinessRoutine(long memberId, MemberHappinessRoutineRequest request);
	MemberHappinessRoutinesResponse getMemberHappinessRoutine(long memberId);
	void deleteMemberHappinessRoutine(long memberId, Long routineId);
	void achieveMemberHappinessRoutine(long memberId, Long routineId);
	void deleteMemberHappinessRoutine(MemberHappinessRoutine routine);
}
