package com.soptie.server.api.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.domain.memo.MemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/memos")
public class MemoApi {

	private final MemoService memoService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public SuccessResponse<?> createMemo(final Principal principal) {

	}

	@PatchMapping("/{memoId}")
	@ResponseStatus(HttpStatus.OK)
	public SuccessResponse<?> modifyMemo(final Principal principal, @PathVariable final long memoId) {

	}

	@DeleteMapping("/{memoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public SuccessResponse<?> deleteMemo(final Principal principal, @PathVariable final long memoId) {

	}
}
