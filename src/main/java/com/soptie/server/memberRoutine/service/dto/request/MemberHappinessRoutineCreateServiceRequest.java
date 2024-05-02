package com.soptie.server.memberRoutine.service.dto.request;

import static lombok.AccessLevel.*;

import com.soptie.server.memberRoutine.controller.v1.dto.request.MemberHappinessRoutineRequest;

import lombok.Builder;

@Builder(access = PRIVATE)
public record MemberHappinessRoutineCreateServiceRequest(
        long memberId,
        long challengeId
) {

    public static MemberHappinessRoutineCreateServiceRequest of(long memberId, MemberHappinessRoutineRequest request) {
        return MemberHappinessRoutineCreateServiceRequest.builder()
                .memberId(memberId)
                .challengeId(request.subRoutineId())
                .build();
    }
}
