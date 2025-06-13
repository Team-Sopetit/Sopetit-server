package com.soptie.server.api.controller.version.docs;

import com.soptie.server.api.common.annotation.ApiSuccessResponse;
import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.version.dto.GetAppVersionResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.VERSION_NAME, description = ApiTagConstants.VERSION_DESCRIPTION)
public interface VersionApiDocs {

	@Operation(summary = "모바일 앱 버전 조회", description = "모바일 앱(클라이언트) 버전 정보를 조회합니다.")
	@ApiSuccessResponse
	SuccessResponse<GetAppVersionResponse> getClientAppVersion();
}
