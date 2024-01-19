package com.soptie.server.auth.dto;

import lombok.Builder;
import lombok.NonNull;

@Builder
public record TokenResponse(
        @NonNull String accessToken
) {

    public static TokenResponse of(String accessToken) {
        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }
}
