package com.soptie.server.routine.dto;

import java.util.List;

import com.soptie.server.routine.entity.happiness.HappinessTheme;

import lombok.Builder;

public record HappinessThemesResponse(
        List<HappinessThemeResponse> themes
) {
    public static HappinessThemesResponse of(List<HappinessTheme> themes) {
        return new HappinessThemesResponse(themes.stream().map(HappinessThemeResponse::of).toList());
    }

    @Builder
    private record HappinessThemeResponse(
            Long themeId,
            String name
    ) {
        private static HappinessThemeResponse of(HappinessTheme theme) {
            return HappinessThemeResponse.builder()
                    .themeId(theme.getId())
                    .name(theme.getName())
                    .build();
        }
    }
}
