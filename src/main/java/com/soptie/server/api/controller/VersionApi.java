package com.soptie.server.api.controller;

import static com.soptie.server.common.message.VersionMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.VersionApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.version.AppVersionGetResponse;
import com.soptie.server.domain.usecase.VersionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/versions")
public class VersionApi implements VersionApiDocs {

	private final VersionService versionService;

	@GetMapping("/client/app")
	public ResponseEntity<SuccessResponse<AppVersionGetResponse>> getClientAppVersion() {
		val response = AppVersionGetResponse.of(versionService.getClientAppVersion());
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_APP_VERSION.getMessage(), response));
	}
}
