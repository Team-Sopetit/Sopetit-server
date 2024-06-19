package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineByThemeListGetServiceResponse(
        @NonNull List<MemberDailyRoutineByThemeGetServiceResponse> routines
) {

    public static MemberDailyRoutineByThemeListGetServiceResponse of(List<MemberDailyRoutineByThemeGetServiceResponse> routines) {
        return MemberDailyRoutineByThemeListGetServiceResponse.builder()
                .routines(routines)
                .build();
    }
}
