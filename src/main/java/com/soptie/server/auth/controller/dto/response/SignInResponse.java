package com.soptie.server.auth.controller.dto.response;

import com.soptie.server.auth.service.dto.response.SignInServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record SignInResponse(
        @NonNull String accessToken,
        @NonNull String refreshToken,
        boolean isMemberDollExist
) {

    public static SignInResponse of(SignInServiceResponse response) {
        return SignInResponse.builder()
                .accessToken(response.accessToken())
                .refreshToken(response.refreshToken())
                .isMemberDollExist(response.isMemberDollExist())
                .build();
    }
}
