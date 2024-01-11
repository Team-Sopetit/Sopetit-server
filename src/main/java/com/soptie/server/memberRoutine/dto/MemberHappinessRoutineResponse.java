package com.soptie.server.memberRoutine.dto;

public record MemberHappinessRoutineResponse(
        long routineId
) {
    public static MemberHappinessRoutineResponse of(Long routineId) {
        return new MemberHappinessRoutineResponse(routineId);
    }
}
