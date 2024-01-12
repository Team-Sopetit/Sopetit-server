package com.soptie.server.memberRoutine.service;

import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;

public interface MemberHappinessRoutineService {

    MemberHappinessRoutineResponse createMemberHappinessRoutine(Long memberId, MemberHappinessRoutineRequest request);
}
