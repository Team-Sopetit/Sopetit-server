package com.soptie.server.memberRoutine.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberHappinessRoutineRepositoryImpl implements MemberHappinessRoutineCustomRepository {
    @Override
    public MemberHappinessRoutine findAllByMember(Member member) {
        return null;
    }
}
