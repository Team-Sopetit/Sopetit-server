package com.soptie.server.routine.service.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.Challenge;

import lombok.Builder;

import java.util.List;

@Builder(access = PRIVATE)
public record HappinessSubRoutineListGetServiceResponse(
        String routineContent,
        String themeName,
        String themeColor,
        String iconImageUrl,
        String backgroundImageUrl,
        List<HappinessSubRoutineServiceResponse> challenges
) {

    public static HappinessSubRoutineListGetServiceResponse of(Routine routine, List<Challenge> challenges) {
        return HappinessSubRoutineListGetServiceResponse.builder()
                .routineContent(routine.getContent())
                .themeName(routine.getTheme().getName())
                .themeColor(routine.getTheme().getColor())
                .iconImageUrl(routine.getTheme().getImageInfo().getIconImageUrl())
                .backgroundImageUrl(routine.getTheme().getImageInfo().getBackgroundImageUrl())
                .challenges(challenges.stream().map(HappinessSubRoutineServiceResponse::of).toList())
                .build();
    }

    @Builder(access = PRIVATE)
    public record HappinessSubRoutineServiceResponse(
            long challengeId,
            String content,
            String description,
            String requiredTime,
            String place
    ) {

        private static HappinessSubRoutineServiceResponse of(Challenge challenge) {
            return HappinessSubRoutineServiceResponse.builder()
                    .challengeId(challenge.getId())
                    .content(challenge.getContent())
                    .description(challenge.getDescription())
                    .requiredTime(challenge.getRequiredTime())
                    .place(challenge.getPlace())
                    .build();
        }
    }
}
