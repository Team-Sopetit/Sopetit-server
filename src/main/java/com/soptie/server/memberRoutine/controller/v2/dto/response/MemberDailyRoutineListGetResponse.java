package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineByThemeGetServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineByThemeListGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetResponse(
        @NonNull List<MemberDailyRoutineByThemeGetServiceResponse> routines
) {

    public static MemberDailyRoutineListGetResponse of(MemberDailyRoutineByThemeListGetServiceResponse tempB) {
        return MemberDailyRoutineListGetResponse.builder()
                .routines(tempB.routines())
                .build();
    }
}
