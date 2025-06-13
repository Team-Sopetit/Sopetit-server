package com.soptie.server.api.controller.auth.dto;

import com.soptie.server.domain.member.SocialType;

import jakarta.validation.constraints.NotNull;

public record SignInRequest(
	@NotNull SocialType socialType
) {

	public static SignInRequest of(SocialType socialType) {
		return new SignInRequest(socialType);
	}
}
