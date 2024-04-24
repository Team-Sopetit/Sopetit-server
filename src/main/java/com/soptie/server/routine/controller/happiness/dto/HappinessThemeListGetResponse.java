package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.entity.happiness.HappinessTheme;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record HappinessThemeListGetResponse(
        @NonNull List<HappinessThemeResponse> themes
) {
    public static HappinessThemeListGetResponse of(List<HappinessTheme> themes) {
        return new HappinessThemeListGetResponse(themes.stream().map(HappinessThemeResponse::of).toList());
    }

    @Builder
    public record HappinessThemeResponse(
            long themeId,
            @NonNull String name
    ) {
        private static HappinessThemeResponse of(HappinessTheme theme) {
            return HappinessThemeResponse.builder()
                    .themeId(theme.getId())
                    .name(theme.getName())
                    .build();
        }
    }
}
