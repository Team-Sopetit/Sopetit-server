package com.soptie.server.routine.controller.v1.dto.response;

import com.soptie.server.routine.service.dto.response.HappinessRoutinesGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record HappinessRoutinesGetResponse(
    @NonNull List<HappinessRoutineResponse> routines
) {

    public static HappinessRoutinesGetResponse of(HappinessRoutinesGetServiceResponse response) {
        List<HappinessRoutineResponse> routineResponses = response.routines().stream()
                .map(HappinessRoutineResponse::of)
                .toList();
        return new HappinessRoutinesGetResponse(routineResponses);
    }

    @Builder
    public record HappinessRoutineResponse(
            long routineId,
            @NonNull String name,
            @NonNull String nameColor,
            @NonNull String title,
            @NonNull String iconImageUrl
    ) {

        public static HappinessRoutineResponse of(HappinessRoutinesGetServiceResponse.HappinessRoutineResponse response) {
            return new HappinessRoutineResponse(
                    response.routineId(),
                    response.name(),
                    response.nameColor(),
                    response.title(),
                    response.iconImageUrl()
            );
        }
    }
}
