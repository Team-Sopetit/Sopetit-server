package com.soptie.server.support;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;

public class MemberHappinessRoutineFixture {

    private Long id;
    private Member member = MemberFixture.member().build();
    private HappinessRoutine routine = HappinessRoutineFixture.happinessRoutine().build();

    private MemberHappinessRoutineFixture() {
    }

    public static MemberHappinessRoutineFixture memberHappinessRoutine() {
        return new MemberHappinessRoutineFixture();
    }

    public MemberHappinessRoutineFixture id(Long id) {
        this.id = id;
        return this;
    }

    public MemberHappinessRoutineFixture member(Member member) {
        this.member = member;
        return this;
    }

    public MemberHappinessRoutineFixture routine(HappinessRoutine routine) {
        this.routine = routine;
        return this;
    }

    public MemberHappinessRoutine build() {
        return new MemberHappinessRoutine(id, member, routine);
    }
}
