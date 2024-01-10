package com.soptie.server.memberRoutine.service;

import java.nio.file.AccessDeniedException;

import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineResponse;

public interface MemberDailyRoutineService {
	MemberDailyRoutineResponse createMemberDailyRoutine(long memberId, MemberDailyRoutineRequest request);
	void deleteMemberDailyRoutine(long memberId, Long routineId) throws AccessDeniedException;
}
