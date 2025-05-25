package com.soptie.server.api.controller.customroutine;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.customroutine.docs.CustomRoutineApiDocs;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineRequest;
import com.soptie.server.api.controller.customroutine.dto.CustomRoutineResponse;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.domain.customroutine.CustomRoutineCommandService;
import com.soptie.server.domain.memberroutine.MemberRoutine;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/custom")
public class CustomRoutineApi implements CustomRoutineApiDocs {

	private final CustomRoutineCommandService commandService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public SuccessResponse<CustomRoutineResponse> create(
		Principal principal,
		@RequestBody CustomRoutineRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		MemberRoutine response = commandService.create(memberId, request);
		return SuccessResponse.from(CustomRoutineResponse.from(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping("/{customRoutineId}")
	public SuccessResponse<CustomRoutineResponse> update(
		Principal principal,
		@PathVariable long customRoutineId,
		@RequestBody CustomRoutineRequest request
	) {
		long memberId = Long.parseLong(principal.getName());
		MemberRoutine response = commandService.update(memberId, customRoutineId, request);
		return SuccessResponse.from(CustomRoutineResponse.from(response));
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/{customRoutineId}")
	public SuccessResponse<?> delete(Principal principal, @PathVariable long customRoutineId) {
		long memberId = Long.parseLong(principal.getName());
		commandService.delete(memberId, customRoutineId);
		return SuccessResponse.from();
	}
}
