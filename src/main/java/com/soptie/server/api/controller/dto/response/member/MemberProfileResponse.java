package com.soptie.server.api.controller.dto.response.member;

import java.time.LocalDateTime;

import com.soptie.server.domain.member.Member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record MemberProfileResponse(
	@Schema(description = "회원가입 일자", example = "yyyy-MM-dd:HH:mm")
	LocalDateTime createdAt
) {

	public static MemberProfileResponse of(Member member) {
		return MemberProfileResponse.builder()
			.createdAt(member.getCreatedAt())
			.build();
	}
}
