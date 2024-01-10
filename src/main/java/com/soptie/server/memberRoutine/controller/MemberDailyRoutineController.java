package com.soptie.server.memberRoutine.controller;

import static com.soptie.server.common.dto.Response.*;
import static com.soptie.server.memberRoutine.message.ResponseMessage.*;

import java.net.URI;
import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.soptie.server.common.dto.Response;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
import com.soptie.server.memberRoutine.dto.MemberDailyRoutineRequest;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberDailyRoutineController {

	private final MemberDailyRoutineService memberDailyRoutineService;

	@PostMapping
	public ResponseEntity<Response> createMemberDailyRoutine(
		Principal principal, @RequestBody MemberDailyRoutineRequest request) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberDailyRoutineService.createMemberDailyRoutine(memberId, request);
		return ResponseEntity
			.created(getURI())
			.body(success(SUCCESS_CREATE_ROUTINE.getMessage(), response));
	}

	private URI getURI() {
		return ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/")
			.buildAndExpand()
			.toUri();
	}
}
