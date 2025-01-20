package com.soptie.server.api.controller.dto.response.member;

import java.time.LocalDateTime;

import com.soptie.server.domain.member.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberProfileResponse(
	@Schema(description = "회원가입 일자", example = "2024-08-28T15:34:05.272309")
	LocalDateTime createdAt
) {

	public static MemberProfileResponse from(Member member) {
		return MemberProfileResponse.builder()
			.createdAt(member.getCreatedAt())
			.build();
	}
}
