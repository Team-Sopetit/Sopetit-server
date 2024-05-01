package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record HappinessRoutinesGetResponse(
    @NonNull List<HappinessRoutineResponse> routines
) {

    public static HappinessRoutinesGetResponse of(List<HappinessRoutine> routines) {
        return new HappinessRoutinesGetResponse(routines.stream().map(HappinessRoutineResponse::of).toList());
    }

    @Builder
    public record HappinessRoutineResponse(
            long routineId,
            @NonNull String name,
            @NonNull String nameColor,
            @NonNull String title,
            @NonNull String iconImageUrl
    ) {

        private static HappinessRoutineResponse of(HappinessRoutine routine) {
            return HappinessRoutineResponse.builder()
                    .routineId(routine.getId())
                    .name(routine.getTheme().getName())
                    .nameColor(routine.getTheme().getNameColor())
                    .title(routine.getTitle())
                    .iconImageUrl(routine.getTheme().getImageInfo().getIconImageUrl())
                    .build();
        }
    }
}
