package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListAcquireServiceResponse(
        @NonNull List<MemberDailyRoutinesAcquireServiceResponse> routines
) {

    public static MemberDailyRoutineListAcquireServiceResponse of(List<MemberDailyRoutinesAcquireServiceResponse> routines) {
        return MemberDailyRoutineListAcquireServiceResponse.builder()
                .routines(routines)
                .build();
    }
}
