package com.soptie.server.api.controller.dto.response.memo;

import com.soptie.server.domain.memo.Memo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;

@Builder(access = AccessLevel.PRIVATE)
public record CreateMemoResponse(
	@Schema(description = "추가한 메모 id", example = "1")
	long memoId
) {

	public static CreateMemoResponse from(Memo memo) {
		return CreateMemoResponse.builder()
			.memoId(memo.getId())
			.build();
	}
}
