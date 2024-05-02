package com.soptie.server.memberRoutine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineCreateServiceResponse(
        long routineId
) {

    public static MemberHappinessRoutineCreateServiceResponse of(MemberRoutine memberRoutine) {
        return MemberHappinessRoutineCreateServiceResponse.builder()
                .routineId(memberRoutine.getId())
                .build();
    }
}
