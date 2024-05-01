package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.service.happiness.dto.HappinessSubRoutinesServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record HappinessSubRoutinesGetResponse(
        @NonNull String title,
        @NonNull String name,
        @NonNull String nameColor,
        @NonNull String iconImageUrl,
        @NonNull String contentImageUrl,
        @NonNull List<HappinessSubRoutineResponse> subRoutines
) {

    public static HappinessSubRoutinesGetResponse of(HappinessSubRoutinesServiceResponse response) {
        return HappinessSubRoutinesGetResponse.builder()
                .title(response.title())
                .name(response.name())
                .nameColor(response.nameColor())
                .iconImageUrl(response.iconImageUrl())
                .contentImageUrl(response.contentImageUrl())
                .subRoutines(response.subRoutines().stream()
                        .map(HappinessSubRoutineResponse::of)
                        .toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record HappinessSubRoutineResponse(
            long subRoutineId,
            @NonNull String content,
            @NonNull String detailContent,
            @NonNull String timeTaken,
            @NonNull String place
    ) {

        public static HappinessSubRoutineResponse of(HappinessSubRoutinesServiceResponse.HappinessSubRoutineResponse response) {
            return new HappinessSubRoutineResponse(
                    response.subRoutineId(),
                    response.content(),
                    response.detailContent(),
                    response.timeTaken(),
                    response.place()
            );
        }
    }
}
