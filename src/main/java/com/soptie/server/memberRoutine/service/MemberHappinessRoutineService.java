package com.soptie.server.memberRoutine.service;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineRequest;
import com.soptie.server.memberRoutine.dto.MemberHappinessRoutineResponse;

import java.util.List;

public interface MemberHappinessRoutineService {

    MemberHappinessRoutineResponse createMemberHappinessRoutine(long memberId, MemberHappinessRoutineRequest request);
    void createMemberHappinessRoutines(Member member, List<Long> routines);
}
