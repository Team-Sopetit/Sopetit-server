package com.soptie.server.memberRoutine.service.dto.response;

import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ViewWithThemeServiceResponse(
        long themeId,
        @NonNull String themeName,
        @NonNull List<InfoServiceResponse> routines
) {

    public static ViewWithThemeServiceResponse of(List<MemberRoutineResponse> routines) {
        return ViewWithThemeServiceResponse.builder()
                .themeId(routines.get(0).themeId())
                .themeName(routines.get(0).themeName())
                .routines(routines.stream().map(InfoServiceResponse::of).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record InfoServiceResponse(
            long routineId,
            @NonNull String content,
            int achieveCount,
            boolean isAchieve
    ) {

        private static InfoServiceResponse of(MemberRoutineResponse routine) {
            return InfoServiceResponse.builder()
                    .routineId(routine.id())
                    .content(routine.content())
                    .achieveCount(routine.achieveCount())
                    .isAchieve(routine.isAchieve())
                    .build();
        }
    }
}
