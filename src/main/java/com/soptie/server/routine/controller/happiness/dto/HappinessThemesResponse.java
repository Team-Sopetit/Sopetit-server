package com.soptie.server.routine.controller.happiness.dto;

import com.soptie.server.routine.service.happiness.dto.HappinessThemesServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public record HappinessThemesResponse(
        @NonNull List<HappinessThemeResponse> themes
) {
    public static HappinessThemesResponse of(HappinessThemesServiceResponse response) {
        List<HappinessThemeResponse> themeResponses = response.themes().stream()
                .map(HappinessThemeResponse::of)
                .collect(Collectors.toList());
        return new HappinessThemesResponse(themeResponses);
    }

    @Builder
    public record HappinessThemeResponse(
            long themeId,
            @NonNull String name
    ) {
        public static HappinessThemeResponse of(HappinessThemesServiceResponse.HappinessThemeResponse response) {
            return new HappinessThemeResponse(response.themeId(), response.name());
        }
    }
}
