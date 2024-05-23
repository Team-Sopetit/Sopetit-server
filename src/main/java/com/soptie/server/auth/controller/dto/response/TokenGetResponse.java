package com.soptie.server.auth.controller.dto.response;

import com.soptie.server.auth.service.dto.response.TokenGetServiceResponse;
import lombok.Builder;
import lombok.NonNull;

import static lombok.AccessLevel.PRIVATE;

@Builder(access = PRIVATE)
public record TokenGetResponse(
        @NonNull String accessToken
) {

    public static TokenGetResponse of(TokenGetServiceResponse response) {
        return TokenGetResponse.builder()
                .accessToken(response.accessToken())
                .build();
    }
}
