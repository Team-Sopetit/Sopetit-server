package com.soptie.server.routine.service.happiness.dto;

import com.soptie.server.routine.entity.happiness.HappinessTheme;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

public record HappinessThemesServiceResponse(
        @NonNull List<HappinessThemeResponse> themes
) {
    public static HappinessThemesServiceResponse of(List<HappinessTheme> themes) {
        return new HappinessThemesServiceResponse(themes.stream().map(HappinessThemeResponse::of).toList());
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
