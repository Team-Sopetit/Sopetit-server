package com.soptie.server.auth.dto;

import com.soptie.server.auth.vo.Token;
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
