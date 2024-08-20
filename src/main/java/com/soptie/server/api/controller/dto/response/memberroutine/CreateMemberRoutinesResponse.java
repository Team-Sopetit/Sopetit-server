package com.soptie.server.api.controller.dto.response.memberroutine;

import java.util.List;

import com.soptie.server.domain.memberroutine.MemberRoutine;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;

@Builder(access = AccessLevel.PRIVATE)
public record CreateMemberRoutinesResponse(
	@Schema(description = "추가한 회원 루틴 id 목록", example = "[1, 2, 3]")
	@NonNull List<Long> ids
) {

	public static CreateMemberRoutinesResponse of(List<MemberRoutine> routines) {
		return CreateMemberRoutinesResponse.builder()
			.ids(routines.stream().map(MemberRoutine::getId).toList())
			.build();
	}
}
