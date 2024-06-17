package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.v2.dto.response.MemberDailyRoutineListGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetResponse(
        @NonNull List<MemberDailyRoutineListGetResponse.MemberDailyRoutineResponse> routines
) {

    public static MemberDailyRoutineListGetResponse of(MemberDailyRoutineListGetServiceResponse response) {
        return MemberDailyRoutineListGetResponse.builder()
                .routines(response.routines().stream().map(MemberDailyRoutineListGetResponse.MemberDailyRoutineResponse::of).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    private record MemberDailyRoutineResponse(
            long routineId,
            @NonNull String content,
            boolean isAchieve
    ) {

        private static MemberDailyRoutineResponse of(MemberDailyRoutineListGetServiceResponse.MemberDailyRoutineServiceResponse response) {
            return MemberDailyRoutineListGetResponse.MemberDailyRoutineResponse.builder()
                    .routineId(response.routineId())
                    .content(response.content())
                    .isAchieve(response.isAchieve())
                    .build();
        }
    }
}
