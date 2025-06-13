package com.soptie.server.api.controller.version;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.api.controller.version.docs.VersionApiDocs;
import com.soptie.server.api.controller.version.dto.GetAppVersionResponse;
import com.soptie.server.domain.version.VersionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/versions")
public class VersionApi implements VersionApiDocs {
	private final VersionService versionService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/client/app")
	public SuccessResponse<GetAppVersionResponse> getClientAppVersion() {
		val response = versionService.getClientAppVersion();
		return SuccessResponse.success(MessageConstants.GET_VERSION.getMessage(), response);
	}
}
