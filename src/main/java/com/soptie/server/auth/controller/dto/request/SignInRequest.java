package com.soptie.server.auth.controller.dto.request;

import com.soptie.server.member.entity.SocialType;

import lombok.NonNull;

public record SignInRequest(
	@NonNull SocialType socialType
) {

	public static SignInRequest of(SocialType socialType) {
		return new SignInRequest(socialType);
	}
}
