package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutinesServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Builder
public record HappinessSubRoutinesResponse(
        @NonNull String title,
        @NonNull String name,
        @NonNull String nameColor,
        @NonNull String iconImageUrl,
        @NonNull String contentImageUrl,
        @NonNull List<HappinessSubRoutineResponse> subRoutines
) {

    public static HappinessSubRoutinesResponse of(HappinessSubRoutinesServiceResponse subRoutines) {
        return HappinessSubRoutinesResponse.builder()
                .title(subRoutines.title())
                .name(subRoutines.name())
                .nameColor(subRoutines.nameColor())
                .iconImageUrl(subRoutines.iconImageUrl())
                .contentImageUrl(subRoutines.contentImageUrl())
                .subRoutines(subRoutines.subRoutines().stream()
                        .map(HappinessSubRoutineResponse::of)
                        .collect(Collectors.toList()))
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

        public static HappinessSubRoutineResponse of(HappinessSubRoutinesServiceResponse.HappinessSubRoutineResponse subRoutine) {
            return new HappinessSubRoutineResponse(
                    subRoutine.subRoutineId(),
                    subRoutine.content(),
                    subRoutine.detailContent(),
                    subRoutine.timeTaken(),
                    subRoutine.place()
            );
        }
    }
}
