package com.soptie.server.version.controller;

import static com.soptie.server.version.message.SuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.version.controller.docs.VersionControllerDocs;
import com.soptie.server.version.controller.dto.response.AppVersionGetResponse;
import com.soptie.server.version.service.VersionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/versions")
public class VersionController implements VersionControllerDocs {

	private final VersionService versionService;

	@GetMapping("/client/app")
	public ResponseEntity<SuccessResponse<AppVersionGetResponse>> getClientAppVersion() {
		val response = AppVersionGetResponse.of(versionService.getClientAppVersion());
		return ResponseEntity.ok(SuccessResponse.success(SUCCESS_GET_APP_VERSION.getMessage(), response));
	}
}
