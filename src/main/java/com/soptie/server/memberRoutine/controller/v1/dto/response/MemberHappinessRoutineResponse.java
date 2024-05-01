package com.soptie.server.memberRoutine.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.service.dto.response.MemberHappinessRoutineCreateServiceResponse;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineResponse(
        long routineId
) {

    public static MemberHappinessRoutineResponse of(MemberHappinessRoutineCreateServiceResponse response) {
        return MemberHappinessRoutineResponse.builder()
                .routineId(response.routineId())
                .build();
    }
}
