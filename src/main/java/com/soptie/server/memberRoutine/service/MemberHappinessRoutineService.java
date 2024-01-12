package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutinesResponse;

import java.util.List;

public interface MemberHappinessRoutineService {

    MemberHappinessRoutineResponse createMemberHappinessRoutine(long memberId, MemberHappinessRoutineRequest request);
	MemberHappinessRoutinesResponse getMemberHappinessRoutine(long memberId);
}
