package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineWithThemeListGetServiceResponse(
        @NonNull List<MemberDailyRoutineWithThemeGetServiceResponse> routines
) {

    public static MemberDailyRoutineWithThemeListGetServiceResponse of(List<MemberDailyRoutineWithThemeGetServiceResponse> routines) {
        return MemberDailyRoutineWithThemeListGetServiceResponse.builder()
                .routines(routines)
                .build();
    }
}
