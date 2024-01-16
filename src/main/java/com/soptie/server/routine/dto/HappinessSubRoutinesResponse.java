package com.soptie.server.routine.dto;

import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record HappinessSubRoutinesResponse(
        @NonNull String title,
        @NonNull String name,
        @NonNull String nameColor,
        @NonNull String iconImageUrl,
        @NonNull String contentImageUrl,
        @NonNull List<HappinessSubRoutineResponse> subRoutines
) {

    public static HappinessSubRoutinesResponse of(List<HappinessSubRoutine> subRoutines) {
        return HappinessSubRoutinesResponse.builder()
                .title(subRoutines.get(0).getRoutine().getTitle())
                .name(subRoutines.get(0).getRoutine().getTheme().getName())
                .nameColor(subRoutines.get(0).getRoutine().getTheme().getNameColor())
                .iconImageUrl(subRoutines.get(0).getRoutine().getTheme().getImageInfo().getIconImageUrl())
                .contentImageUrl(subRoutines.get(0).getRoutine().getTheme().getImageInfo().getContentImageUrl())
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

        private static HappinessSubRoutinesResponse.HappinessSubRoutineResponse of(HappinessSubRoutine subRoutine) {
            return HappinessSubRoutineResponse.builder()
                    .subRoutineId(subRoutine.getId())
                    .content(subRoutine.getPlace())
                    .detailContent(subRoutine.getDetailContent())
                    .timeTaken(subRoutine.getTimeTaken())
                    .place(subRoutine.getPlace())
                    .build();
        }
    }
}
