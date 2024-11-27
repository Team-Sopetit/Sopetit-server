package com.soptie.server.api.controller.dto.request.memo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record ModifyMemoRequest(
	@Schema(description = "수정할 메모 내용", example = "대단한 루틴이구만.")
	@NotNull String content
) {
}
