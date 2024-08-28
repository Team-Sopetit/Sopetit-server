package com.soptie.server.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.VersionApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.version.GetAppVersionResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
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
		return SuccessResponse.success(SuccessMessage.GET_VERSION.getMessage(), response);
	}
}
