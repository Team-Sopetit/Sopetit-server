package com.soptie.server.member.controller.dto.request;

import java.util.List;

import com.soptie.server.doll.entity.DollType;

import lombok.NonNull;

public record MemberProfileCreateRequest(
	@NonNull DollType dollType,
	@NonNull String name,
	@NonNull List<Long> routines
) {
}
