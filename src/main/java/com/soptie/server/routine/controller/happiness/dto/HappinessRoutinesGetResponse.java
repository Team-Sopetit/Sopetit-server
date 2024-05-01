package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.service.happiness.dto.HappinessRoutinesServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record HappinessRoutinesGetResponse(
        @NonNull List<HappinessRoutineResponse> routines
) {

    public static HappinessRoutinesGetResponse of(HappinessRoutinesServiceResponse serviceResponse) {
        List<HappinessRoutineResponse> routineResponses = serviceResponse.routines().stream()
                .map(HappinessRoutineResponse::of)
                .toList();
        return new HappinessRoutinesGetResponse(routineResponses);
    }

    @Builder(access = PRIVATE)
    public record HappinessRoutineResponse(
            long routineId,
            @NonNull String name,
            @NonNull String nameColor,
            @NonNull String title,
            @NonNull String iconImageUrl
    ) {

        public static HappinessRoutineResponse of(HappinessRoutinesServiceResponse.HappinessRoutineResponse routineResponse) {
            return new HappinessRoutineResponse(
                    routineResponse.routineId(),
                    routineResponse.name(),
                    routineResponse.nameColor(),
                    routineResponse.title(),
                    routineResponse.iconImageUrl()
            );
        }
    }
}
