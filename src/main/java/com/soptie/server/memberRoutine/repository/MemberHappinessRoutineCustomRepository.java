package com.soptie.server.memberRoutine.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

public interface MemberHappinessRoutineCustomRepository {
    MemberHappinessRoutine findAllByMember(Member member);
}
