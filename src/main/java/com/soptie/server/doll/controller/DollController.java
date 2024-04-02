package com.soptie.server.doll.controller;

import static com.soptie.server.common.dto.SuccessResponse.*;
import static com.soptie.server.doll.message.SuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.doll.dto.DollImageResponse;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.service.DollService;

import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dolls")
public class DollController implements DollApi {

	private final DollService dollService;

	@GetMapping("/image/{type}")
	public ResponseEntity<SuccessResponse<DollImageResponse>> getDollImages(@PathVariable DollType type) {
		val response = dollService.getDollImage(type);
		return ResponseEntity.ok(of(SUCCESS_GET_IMAGE.getMessage(), response));
	}
}
