package com.soptie.server.memberRoutine.service;

import com.soptie.server.memberRoutine.dto.*;

public interface MemberHappinessRoutineService {

	MemberHappinessRoutineResponse createMemberHappinessRoutine(Long memberId, MemberHappinessRoutineRequest request);
	MemberHappinessRoutinesResponse getMemberHappinessRoutine(Long memberId);
	void deleteMemberHappinessRoutine(Long memberId, Long routineId);

	void achieveMemberHappinessRoutine(Long memberId, Long routineId);

}
