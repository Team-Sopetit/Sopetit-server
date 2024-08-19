package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.BaseResponse;

@RestController
@RequestMapping("/api/v1/test")
public class TestApi {

	@GetMapping
	public ResponseEntity<BaseResponse> test() {
		return ResponseEntity.ok(success("Success to server connect."));
	}
}
