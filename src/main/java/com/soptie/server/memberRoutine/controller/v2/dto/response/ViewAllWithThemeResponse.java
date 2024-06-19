package com.soptie.server.memberRoutine.controller.v2.dto.response;

import com.soptie.server.memberRoutine.service.dto.response.ViewWithThemeServiceResponse;
import com.soptie.server.memberRoutine.service.dto.response.ViewAllWithThemeServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record ViewAllWithThemeResponse(
        @NonNull List<ViewWithThemeServiceResponse> routines
) {

    public static ViewAllWithThemeResponse of(ViewAllWithThemeServiceResponse response) {
        return ViewAllWithThemeResponse.builder()
                .routines(response.routines())
                .build();
    }
}
