package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.request.memo.CreateMemoRequest;
import com.soptie.server.api.controller.dto.request.memo.ModifyMemoRequest;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memo.CreateMemoResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.memo.MemoService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/memos")
public class MemoApi {

	private final MemoService memoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SuccessResponse<CreateMemoResponse> createMemo(
		final Principal principal,
		@RequestBody final CreateMemoRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memoService.create(memberId, request);
		return SuccessResponse.success(SuccessMessage.CREATE_MEMO.getMessage(), response);
	}

	@PatchMapping("/{memoId}")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<?> modifyMemo(
		final Principal principal,
		@PathVariable final long memoId,
		@RequestBody final ModifyMemoRequest request
	) {
		val memberId = Long.parseLong(principal.getName());
		memoService.modify(memberId, memoId, request);
		return SuccessResponse.success(SuccessMessage.UPDATE_MEMO.getMessage());
	}

	@DeleteMapping("/{memoId}")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<?> deleteMemo(final Principal principal, @PathVariable final long memoId) {
		val memberId = Long.parseLong(principal.getName());
		memoService.delete(memberId, memoId);
		return SuccessResponse.success(SuccessMessage.DELETE_MEMO.getMessage());
	}
}
