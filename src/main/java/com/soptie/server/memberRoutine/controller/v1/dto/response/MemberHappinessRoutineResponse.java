package com.soptie.server.memberRoutine.controller.v1.dto.response;

import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

public record MemberHappinessRoutineResponse(
        long routineId
) {
    public static MemberHappinessRoutineResponse of(MemberHappinessRoutine routine) {
        return new MemberHappinessRoutineResponse(routine.getId());
    }
}
