package com.soptie.server.routine.dto;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import lombok.Builder;

@Builder
public record HappinessRoutineResponse(

        String title,
        String name,
        String nameColor,
        String iconImageUrl,
        String contentImageUrl
) {

    public static HappinessRoutineResponse of (HappinessRoutine happinessRoutine) {
        return HappinessRoutineResponse.builder()
                .title(happinessRoutine.getTitle())
                .name(happinessRoutine.getTheme().getName())
                .nameColor(happinessRoutine.getTheme().getNameColor())
                .iconImageUrl(happinessRoutine.getTheme().getImageInfo().getIconImageUrl())
                .contentImageUrl(happinessRoutine.getTheme().getImageInfo().getContentImageUrl())
                .build();
    }
}
