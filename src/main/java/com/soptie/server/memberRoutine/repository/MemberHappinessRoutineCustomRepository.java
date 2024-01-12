package com.soptie.server.memberRoutine.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

import java.util.List;

public interface MemberHappinessRoutineCustomRepository {
    List<MemberHappinessRoutine> findAllByMember(Member member);
}
