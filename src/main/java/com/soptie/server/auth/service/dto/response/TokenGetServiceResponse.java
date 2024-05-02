package com.soptie.server.auth.service.dto.response;

import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TokenGetServiceResponse(
        @NonNull String accessToken
) {

    public static TokenGetServiceResponse of(String accessToken) {
        return TokenGetServiceResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
