package com.soptie.server.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MakerApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.maker.GetMakerListResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.maker.MakerService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/makers")
public class MakerApi implements MakerApiDocs {

	private final MakerService makerService;

	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<GetMakerListResponse> acquireAll() {
		val response = makerService.acquireAll();
		return SuccessResponse.success(SuccessMessage.SUCCESS_GET_MAKER_THEME.getMessage(), response);
	}
}
