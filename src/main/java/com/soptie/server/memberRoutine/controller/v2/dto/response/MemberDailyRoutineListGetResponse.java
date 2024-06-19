package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineWithThemeGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineWithThemeListGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetResponse(
        @NonNull List<MemberDailyRoutineWithThemeGetServiceResponse> routines
) {

    public static MemberDailyRoutineListGetResponse of(MemberDailyRoutineWithThemeListGetServiceResponse response) {
        return MemberDailyRoutineListGetResponse.builder()
                .routines(response.routines())
                .build();
    }
}
