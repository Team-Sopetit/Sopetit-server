package com.soptie.server.auth.dto;

import com.soptie.server.auth.vo.Token;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record TokenResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken
) {

    public static TokenResponse of(Token token) {
        return TokenResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
