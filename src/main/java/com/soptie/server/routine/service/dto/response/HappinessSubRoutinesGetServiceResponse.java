package com.soptie.server.routine.service.dto.response;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record HappinessSubRoutinesGetServiceResponse(
        @NonNull String title,
        @NonNull String name,
        @NonNull String nameColor,
        @NonNull String iconImageUrl,
        @NonNull String contentImageUrl,
        @NonNull List<HappinessSubRoutineResponse> subRoutines
) {

    public static HappinessSubRoutinesGetServiceResponse of(HappinessRoutine routine, List<HappinessSubRoutine> subRoutines) {
        return HappinessSubRoutinesGetServiceResponse.builder()
                .title(routine.getTitle())
                .name(routine.getTheme().getName())
                .nameColor(routine.getTheme().getNameColor())
                .iconImageUrl(routine.getTheme().getImageInfo().getTwinkleIconImageUrl())
                .contentImageUrl(routine.getTheme().getImageInfo().getContentImageUrl())
                .subRoutines(subRoutines.stream().map(HappinessSubRoutineResponse::of).toList())
                .build();
    }

    @Builder
    public record HappinessSubRoutineResponse(
            long subRoutineId,
            @NonNull String content,
            @NonNull String detailContent,
            @NonNull String timeTaken,
            @NonNull String place
    ) {

        private static HappinessSubRoutineResponse of(HappinessSubRoutine subRoutine) {
            return HappinessSubRoutineResponse.builder()
                    .subRoutineId(subRoutine.getId())
                    .content(subRoutine.getContent())
                    .detailContent(subRoutine.getDetailContent())
                    .timeTaken(subRoutine.getTimeTaken())
                    .place(subRoutine.getPlace())
                    .build();
        }
    }
}
