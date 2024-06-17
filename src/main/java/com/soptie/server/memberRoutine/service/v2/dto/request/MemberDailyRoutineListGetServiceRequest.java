package com.soptie.server.memberRoutine.service.v2.dto.request;

import lombok.Builder;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceRequest(
        long memberId
) {

    public static MemberDailyRoutineListGetServiceRequest of(long memberId) {
        return MemberDailyRoutineListGetServiceRequest.builder()
                .memberId(memberId)
                .build();
    }
}
