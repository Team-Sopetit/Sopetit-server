package com.soptie.server.auth.dto;

import com.soptie.server.member.entity.SocialType;

import lombok.NonNull;

public record SignInRequest(
        @NonNull SocialType socialType
) {

    public static SignInRequest of(SocialType socialType) {
        return new SignInRequest(socialType);
    }
}
