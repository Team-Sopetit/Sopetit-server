package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.*;

import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse;
import com.soptie.server.theme.service.dto.response.ThemeListGetServiceResponse.ThemeServiceResponse;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder(access = PRIVATE)
public record HappinessThemeListGetResponse(
        @NonNull List<HappinessThemeResponse> themes
) {

    public static HappinessThemeListGetResponse of(ThemeListGetServiceResponse response) {
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
