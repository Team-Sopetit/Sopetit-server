package com.soptie.server.api.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.controller.docs.MemberRoutineApiDocs;
import com.soptie.server.api.controller.dto.response.SuccessResponse;
import com.soptie.server.api.controller.dto.response.memberroutine.AchieveMemberRoutineResponse;
import com.soptie.server.api.controller.generic.SuccessMessage;
import com.soptie.server.domain.memberroutine.MemberRoutineService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/routines/daily/member")
public class MemberRoutineApi implements MemberRoutineApiDocs {
	private final MemberRoutineService memberRoutineService;

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping
	public SuccessResponse<?> deleteMemberRoutines(
		Principal principal,
		@RequestParam List<Long> routines
	) {
		val memberId = Long.parseLong(principal.getName());
		memberRoutineService.deleteMemberRoutines(memberId, routines);
		return SuccessResponse.success(SuccessMessage.DELETE_ROUTINE.getMessage());
	}

	@ResponseStatus(HttpStatus.OK)
	@PatchMapping("/routine/{routineId}")
	public SuccessResponse<AchieveMemberRoutineResponse> achieveMemberRoutine(
		Principal principal,
		@PathVariable long routineId
	) {
		val memberId = Long.parseLong(principal.getName());
		val response = memberRoutineService.achieveMemberRoutine(memberId, routineId);
		return SuccessResponse.success(SuccessMessage.ACHIEVE_ROUTINE.getMessage(), response);
	}

	@ResponseStatus(HttpStatus.OK)
	@DeleteMapping("/history/{historyId}")
	public SuccessResponse<?> deleteRoutineHistory(@PathVariable long historyId) {
		memberRoutineService.deleteHistory(historyId);
		return SuccessResponse.success(SuccessMessage.DELETE_ROUTINE.getMessage());
	}
}
