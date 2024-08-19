package com.soptie.server.api.controller;

import static com.soptie.server.api.controller.dto.response.SuccessResponse.*;
import static com.soptie.server.common.message.DollMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.DollApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.doll.DollImageResponse;
import com.soptie.server.persistence.entity.DollType;
import com.soptie.server.domain.usecase.DollService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dolls")
public class DollApi implements DollApiDocs {

	private final DollService dollService;

	@GetMapping("/image/{type}")
	public ResponseEntity<SuccessResponse<DollImageResponse>> getDollImages(@PathVariable DollType type) {
		val response = dollService.getDollImage(type);
		return ResponseEntity.ok(success(SUCCESS_GET_IMAGE.getMessage(), response));
	}
}
