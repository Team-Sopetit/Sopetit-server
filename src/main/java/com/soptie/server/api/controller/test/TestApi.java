package com.soptie.server.api.controller.test;

import static com.soptie.server.api.controller.generic.dto.SuccessResponse.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.ApiTagConstants;
import com.soptie.server.api.controller.generic.dto.BaseResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = ApiTagConstants.TEST_NAME, description = ApiTagConstants.TEST_DESCRIPTION)
@RestController
@RequestMapping("/api/v1/test")
public class TestApi {

	@GetMapping
	public ResponseEntity<BaseResponse> test() {
		return ResponseEntity.ok(success("Success to server connect."));
	}
}
