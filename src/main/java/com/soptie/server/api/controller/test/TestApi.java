package com.soptie.server.api.controller.test;

import static com.soptie.server.api.controller.generic.dto.SuccessResponse.*;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.BaseResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.domain.test.TestService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Profile("dev")
@Tag(name = ApiTagConstants.TEST_NAME, description = ApiTagConstants.TEST_DESCRIPTION)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/test")
public class TestApi {

	private final TestService testService;

	@GetMapping
	public ResponseEntity<BaseResponse> test() {
		return ResponseEntity.ok(success("Success to server connect."));
	}

	@PostMapping
	public SuccessResponse<String> createTestAccessToken(
		@Parameter(hidden = true) @RequestHeader(HttpHeaders.AUTHORIZATION) String testId
	) {
		return SuccessResponse.from(testService.getTestAccessToken(testId));
	}
}
