package com.soptie.server.auth.dto;

import com.soptie.server.member.entity.SocialType;

public record SignInRequest(
        SocialType socialType
) {

    public static SignInRequest of(SocialType socialType) {
        return new SignInRequest(socialType);
    }
}
