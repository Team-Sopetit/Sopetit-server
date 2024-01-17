package com.soptie.server.auth.dto;

import com.soptie.server.auth.vo.Token;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record SignInResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken,
        boolean isMemberDollExist
) {

    public static SignInResponse of(Token token, boolean isMemberDollExist) {
        return SignInResponse.builder()
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .isMemberDollExist(isMemberDollExist)
                .build();
    }
}
