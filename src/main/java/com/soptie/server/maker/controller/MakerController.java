package com.soptie.server.maker.controller;

import static com.soptie.server.common.dto.SuccessResponse.success;
import static com.soptie.server.maker.message.MakerSuccessMessage.SUCCESS_GET_MAKER_THEME;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.common.dto.SuccessResponse;
import com.soptie.server.maker.controller.docs.MakerControllerDocs;
import com.soptie.server.maker.controller.dto.response.MakerListAcquireResponse;
import com.soptie.server.maker.service.MakerService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/makers")
public class MakerController implements MakerControllerDocs {

	private final MakerService makerService;

	@Override
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<MakerListAcquireResponse> acquireAll() {
		val response = makerService.acquireAll();
		return success(SUCCESS_GET_MAKER_THEME.getMessage(), MakerListAcquireResponse.from(response));
	}
}
