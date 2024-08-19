package com.soptie.server.api.controller.dto.request.auth;

import lombok.NonNull;

public record SignInRequest(
	@NonNull SocialType socialType
) {

	public static SignInRequest of(SocialType socialType) {
		return new SignInRequest(socialType);
	}
}
