package com.soptie.server.api.controller.dto.request.auth;

import com.soptie.server.domain.member.SocialType;

import lombok.NonNull;

public record SignInRequest(
	@NonNull SocialType socialType
) {

	public static SignInRequest of(SocialType socialType) {
		return new SignInRequest(socialType);
	}
}
