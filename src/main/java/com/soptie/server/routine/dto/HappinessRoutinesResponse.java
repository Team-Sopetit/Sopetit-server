package com.soptie.server.routine.dto;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import lombok.Builder;

import java.util.List;

public record HappinessRoutinesResponse (
    List<HappinessRoutineResponse> routines
) {
    public static HappinessRoutinesResponse of(List<HappinessRoutine> routines) {
        return new HappinessRoutinesResponse(routines.stream().map(HappinessRoutineResponse::of).toList());
    }

    @Builder
    private record HappinessRoutineResponse(
            Long routineId,
            String name,
            String nameColor,
            String title,
            String iconImageUrl

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
