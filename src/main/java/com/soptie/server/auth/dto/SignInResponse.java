package com.soptie.server.auth.dto;

import com.soptie.server.auth.vo.Token;
import lombok.Builder;

@Builder
public record SignInResponse(
        String accessToken,
        String refreshToken
) {

    public static SignInResponse of(Token token) {
        return SignInResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }
}
