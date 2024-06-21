package com.soptie.server.theme.controller.v1.dto.response;

import static lombok.AccessLevel.PRIVATE;

import com.soptie.server.theme.service.vo.ThemeVO;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

@Builder(access = PRIVATE)
public record HappinessThemeListGetResponse(
        @NonNull List<HappinessThemeResponse> themes
) {

    public static HappinessThemeListGetResponse from(List<ThemeVO> themes) {
        return HappinessThemeListGetResponse.builder()
                .themes(themes.stream().map(HappinessThemeResponse::from).toList())
                .build();
    }

    @Builder
    public record HappinessThemeResponse(
            long themeId,
            @NonNull String name
    ) {

        private static HappinessThemeResponse from(ThemeVO theme) {
            return HappinessThemeResponse.builder()
                    .themeId(theme.themeId())
                    .name(theme.name())
                    .build();
        }
    }
}
