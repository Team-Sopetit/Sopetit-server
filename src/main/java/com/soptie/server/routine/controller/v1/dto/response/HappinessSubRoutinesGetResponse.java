package com.soptie.server.routine.controller.v1.dto.response;

import com.soptie.server.routine.service.dto.response.HappinessSubRoutinesGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder
public record HappinessSubRoutinesGetResponse(
        @NonNull String title,
        @NonNull String name,
        @NonNull String nameColor,
        @NonNull String iconImageUrl,
        @NonNull String contentImageUrl,
        @NonNull List<HappinessSubRoutineResponse> subRoutines
) {

    public static HappinessSubRoutinesGetResponse of(HappinessSubRoutinesGetServiceResponse response) {
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

    @Builder
    public record HappinessSubRoutineResponse(
            long subRoutineId,
            @NonNull String content,
            @NonNull String detailContent,
            @NonNull String timeTaken,
            @NonNull String place
    ) {

        public static HappinessSubRoutineResponse of(HappinessSubRoutinesGetServiceResponse.HappinessSubRoutineResponse response) {
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
