package com.soptie.server.doll.controller;

import static com.soptie.server.common.dto.Response.*;
import static com.soptie.server.doll.message.SuccessMessage.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.Response;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.service.DollService;

import lombok.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dolls")
public class DollController {

	private final DollService dollService;

	@GetMapping("/image/{type}")
	public ResponseEntity<Response> getDollImages(@PathVariable DollType type) {
		val response = dollService.getDollImage(type);
		return ResponseEntity.ok(success(SUCCESS_GET_IMAGE.getMessage(), response));
	}
}
