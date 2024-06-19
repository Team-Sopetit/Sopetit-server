package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceResponse(
        @NonNull List<MemberDailyRoutineGetServiceResponse> routines
) {

    public static MemberDailyRoutineListGetServiceResponse of(List<MemberDailyRoutineGetServiceResponse> routines) {
        return MemberDailyRoutineListGetServiceResponse.builder()
                .routines(routines)
                .build();
    }
}
