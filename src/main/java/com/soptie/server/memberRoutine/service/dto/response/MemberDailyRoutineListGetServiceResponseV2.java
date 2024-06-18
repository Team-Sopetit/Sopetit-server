package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceResponseV2(
        @NonNull List<MemberDailyRoutineGetServiceResponseV2> routines
) {

    public static MemberDailyRoutineListGetServiceResponseV2 of(List<MemberDailyRoutineGetServiceResponseV2> routines) {
        return MemberDailyRoutineListGetServiceResponseV2.builder()
                .routines(routines)
                .build();
    }
}
