package com.soptie.server.memberRoutine.service.v2.dto.response;

import com.soptie.server.memberRoutine.repository.v2.dto.MemberRoutineResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetServiceResponse(
        @NonNull List<MemberDailyRoutineServiceResponse> routines
) {

    public static MemberDailyRoutineListGetServiceResponse of(List<MemberRoutineResponse> routines) {
        return MemberDailyRoutineListGetServiceResponse.builder()
                .routines(routines.stream().map(MemberDailyRoutineServiceResponse::of).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record MemberDailyRoutineServiceResponse(
            long routineId,
            @NonNull String content,
            boolean isAchieve
    ) {

        private static MemberDailyRoutineServiceResponse of(MemberRoutineResponse routine) {
            return MemberDailyRoutineServiceResponse.builder()
                    .routineId(routine.id())
                    .content(routine.content())
                    .isAchieve(routine.isAchieve())
                    .build();
        }
    }
}

