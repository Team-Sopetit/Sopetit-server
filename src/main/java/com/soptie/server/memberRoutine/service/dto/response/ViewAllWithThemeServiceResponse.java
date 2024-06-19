package com.soptie.server.memberRoutine.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ViewAllWithThemeServiceResponse(
        @NonNull List<ViewWithThemeServiceResponse> routines
) {

    public static ViewAllWithThemeServiceResponse of(List<ViewWithThemeServiceResponse> routines) {
        return ViewAllWithThemeServiceResponse.builder()
                .routines(routines)
                .build();
    }
}
