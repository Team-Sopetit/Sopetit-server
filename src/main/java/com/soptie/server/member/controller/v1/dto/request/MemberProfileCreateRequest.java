package com.soptie.server.member.controller.v1.dto.request;

import java.util.List;

import com.soptie.server.doll.entity.DollType;

import lombok.NonNull;

public record MemberProfileCreateRequest(
	@NonNull DollType dollType,
	@NonNull String name,
	@NonNull List<Long> routines
) {
}
