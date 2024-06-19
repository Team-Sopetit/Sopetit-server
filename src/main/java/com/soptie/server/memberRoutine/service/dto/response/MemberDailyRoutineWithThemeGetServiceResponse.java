package com.soptie.server.memberRoutine.service.dto.response;

import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineWithThemeGetServiceResponse(
        long themeId,
        @NonNull String themeName,
        @NonNull List<MemberDailyRoutineServiceResponse> routines
) {

    public static MemberDailyRoutineWithThemeGetServiceResponse of(List<MemberRoutineResponse> routines) {
        return MemberDailyRoutineWithThemeGetServiceResponse.builder()
                .themeId(routines.get(0).themeId())
                .themeName(routines.get(0).themeName())
                .routines(routines.stream().map(MemberDailyRoutineServiceResponse::of).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record MemberDailyRoutineServiceResponse(
            long routineId,
            @NonNull String content,
            int achieveCount,
            boolean isAchieve
    ) {

        private static MemberDailyRoutineServiceResponse of(MemberRoutineResponse routine) {
            return MemberDailyRoutineServiceResponse.builder()
                    .routineId(routine.id())
                    .content(routine.content())
                    .achieveCount(routine.achieveCount())
                    .isAchieve(routine.isAchieve())
                    .build();
        }
    }
}
