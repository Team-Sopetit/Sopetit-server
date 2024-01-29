package com.soptie.server.version.controller;

import static com.soptie.server.version.message.SuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.Response;
import com.soptie.server.version.service.VersionService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/versions")
public class VersionController {

	private final VersionService versionService;

	@GetMapping("/client/app")
	public ResponseEntity<Response> getClientAppVersion() {
		val response = versionService.getClientAppVersion();
		return ResponseEntity.ok(Response.success(SUCCESS_GET_APP_VERSION.getMessage(), response));
	}
}
