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
            String title,
            String titleColor,
            String content,
            String imageUrl

    ) {

        private static HappinessRoutineResponse of(HappinessRoutine routine) {
            return HappinessRoutineResponse.builder()
                    .routineId(routine.getId())
                    .title(routine.getTitle())
                    .build();
        }
    }
}
