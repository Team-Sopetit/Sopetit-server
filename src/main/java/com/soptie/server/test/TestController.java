package com.soptie.server.test;

import static com.soptie.server.common.dto.SuccessResponse.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.BaseResponse;

@RestController
@RequestMapping("/api/v1/test")
public class TestController implements TestApi {

	@GetMapping
	public ResponseEntity<BaseResponse> test() {
		return ResponseEntity.ok(success("Success to server connect."));
	}
}
