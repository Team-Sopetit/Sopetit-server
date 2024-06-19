package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetResponseV2(
        @NonNull List<MemberDailyRoutineListResponse> routines
) {

    public static MemberDailyRoutineListGetResponseV2 of(MemberDailyRoutineListGetServiceResponse response) {
        return MemberDailyRoutineListGetResponseV2.builder()
                .routines(response.routines().stream()
                        .map(MemberDailyRoutineListResponse::of)
                        .toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record MemberDailyRoutineListResponse(
            long themeId,
            @NonNull String themeName,
            @NonNull List<MemberDailyRoutineResponse> routines
    ) {

        public static MemberDailyRoutineListResponse of(MemberDailyRoutineGetServiceResponse routines) {
            return MemberDailyRoutineListResponse.builder()
                    .themeId(routines.themeId())
                    .themeName(routines.themeName())
                    .routines(routines.routines().stream().map(MemberDailyRoutineResponse::of).toList())
                    .build();
        }

        @Builder(access = PRIVATE)
        public record MemberDailyRoutineResponse(
                long routineId,
                @NonNull String content,
                int achieveCount,
                boolean isAchieve
        ) {

            private static MemberDailyRoutineResponse of(
                    MemberDailyRoutineGetServiceResponse.MemberDailyRoutineServiceResponse routine
            ) {
                return MemberDailyRoutineResponse.builder()
                        .routineId(routine.routineId())
                        .content(routine.content())
                        .achieveCount(routine.achieveCount())
                        .isAchieve(routine.isAchieve())
                        .build();
            }
        }
    }
}
