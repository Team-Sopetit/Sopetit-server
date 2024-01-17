package com.soptie.server.memberRoutine.service;

import java.util.Optional;

import com.soptie.server.memberRoutine.dto.*;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

public interface MemberHappinessRoutineService {

	MemberHappinessRoutineResponse createMemberHappinessRoutine(long memberId, MemberHappinessRoutineRequest request);
	Optional<MemberHappinessRoutinesResponse> getMemberHappinessRoutine(long memberId);
	void deleteMemberHappinessRoutine(long memberId, long routineId);
	void achieveMemberHappinessRoutine(long memberId, long routineId);
	void deleteMemberHappinessRoutine(MemberHappinessRoutine routine);
}
