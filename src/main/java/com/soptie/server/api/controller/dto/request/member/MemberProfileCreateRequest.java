package com.soptie.server.api.controller.dto.request.member;

import java.util.List;

import com.soptie.server.domain.doll.DollType;

import lombok.NonNull;

public record MemberProfileCreateRequest(
	@NonNull DollType dollType,
	@NonNull String name,
	@NonNull List<Long> routines
) {
}
