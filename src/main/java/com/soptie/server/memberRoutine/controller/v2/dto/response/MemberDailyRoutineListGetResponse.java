package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineGetServiceResponseV2;
import com.soptie.server.memberRoutine.service.dto.response.MemberDailyRoutineListGetServiceResponseV2;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record MemberDailyRoutineListGetResponse(
        @NonNull List<MemberDailyRoutineGetServiceResponseV2> routines
) {

    public static MemberDailyRoutineListGetResponse of(MemberDailyRoutineListGetServiceResponseV2 tempB) {
        return MemberDailyRoutineListGetResponse.builder()
                .routines(tempB.routines())
                .build();
    }
}
