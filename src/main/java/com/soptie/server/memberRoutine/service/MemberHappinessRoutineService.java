package com.soptie.server.memberRoutine.service;

import com.soptie.server.memberRoutine.dto.*;

public interface MemberHappinessRoutineService {

	MemberHappinessRoutineResponse createMemberHappinessRoutine(Long memberId, MemberHappinessRoutineRequest request);
	MemberHappinessRoutinesResponse getMemberHappinessRoutine(Long memberId);
	void deleteMemberHappinessRoutine(long memberId, Long routineId);

}
