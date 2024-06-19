package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.theme.service.dto.response.ThemeListSearchServiceResponse;
import com.soptie.server.theme.service.dto.response.ThemeListSearchServiceResponse.ThemeServiceResponse;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder(access = PRIVATE)
public record HappinessThemeListGetResponse(
        @NonNull List<HappinessThemeResponse> themes
) {

    public static HappinessThemeListGetResponse of(ThemeListSearchServiceResponse response) {
        return HappinessThemeListGetResponse.builder()
                .themes(response.themes().stream().map(HappinessThemeResponse::of).toList())
                .build();
    }

    @Builder
    public record HappinessThemeResponse(
            long themeId,
            @NonNull String name
    ) {

        private static HappinessThemeResponse of(ThemeServiceResponse response) {
            return HappinessThemeResponse.builder()
                    .themeId(response.themeId())
                    .name(response.name())
                    .build();
        }
    }
}
