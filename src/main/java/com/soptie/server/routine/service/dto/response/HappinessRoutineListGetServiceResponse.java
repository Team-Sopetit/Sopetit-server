package com.soptie.server.routine.service.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.routine.entity.Routine;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder(access = PRIVATE)
public record HappinessRoutineListGetServiceResponse(
        List<HappinessRoutineServiceResponse> routines
) {

    public static HappinessRoutineListGetServiceResponse of(List<Routine> routines) {
        return HappinessRoutineListGetServiceResponse.builder()
                .routines(routines.stream().map(HappinessRoutineServiceResponse::of).toList())
                .build();
    }

    @Builder
    public record HappinessRoutineServiceResponse(
            long routineId,
            @NonNull String themeName,
            @NonNull String themeColor,
            @NonNull String content,
            @NonNull String iconImageUrl
    ) {

        private static HappinessRoutineServiceResponse of(Routine routine) {
            return HappinessRoutineServiceResponse.builder()
                    .routineId(routine.getId())
                    .themeName(routine.getTheme().getName())
                    .themeColor(routine.getTheme().getColor())
                    .content(routine.getContent())
                    .iconImageUrl(routine.getTheme().getImageInfo().getIconImageUrl())
                    .build();
        }
    }
}
