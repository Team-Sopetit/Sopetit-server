package com.soptie.server.memberRoutine.dto;

import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

public record MemberHappinessRoutineResponse(
        long routineId
) {
    public static MemberHappinessRoutineResponse of(MemberHappinessRoutine routine) {
        return new MemberHappinessRoutineResponse(routine.getId());
    }
}
