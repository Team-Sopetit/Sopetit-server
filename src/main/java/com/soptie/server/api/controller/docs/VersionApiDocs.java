package com.soptie.server.api.controller.docs;

import com.soptie.server.api.controller.dto.response.ErrorResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.version.GetAppVersionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "VersionAPI", description = "버전 API")
public interface VersionApiDocs {

	@Operation(
		summary = "모바일 앱 버전 조회",
		description = "모바일 앱(클라이언트) 버전 정보를 조회합니다.",
		responses = {
			@ApiResponse(responseCode = "200", description = "성공"),
			@ApiResponse(
				responseCode = "500",
				description = "서버 내부 오류",
				content = @Content(schema = @Schema(implementation = ErrorResponse.class)))}
	)
	SuccessResponse<GetAppVersionResponse> getClientAppVersion();
}
