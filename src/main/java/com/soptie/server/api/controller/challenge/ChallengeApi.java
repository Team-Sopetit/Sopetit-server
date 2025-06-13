package com.soptie.server.api.controller.challenge;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.soptie.server.api.common.constants.MessageConstants;
import com.soptie.server.api.controller.challenge.docs.ChallengeApiDocs;
import com.soptie.server.api.controller.challenge.dto.ChallengesResponse;
import com.soptie.server.api.controller.generic.dto.SuccessResponse;
import com.soptie.server.domain.challenge.ChallengeService;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/challenges")
public class ChallengeApi implements ChallengeApiDocs {
	private final ChallengeService challengeService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public SuccessResponse<ChallengesResponse> getChallengesByTheme(Principal principal, @RequestParam long themeId) {
		val memberId = Long.parseLong(principal.getName());
		val response = challengeService.getChallengesByTheme(memberId, themeId);
		return SuccessResponse.success(MessageConstants.GET_CHALLENGE.getMessage(), response);
	}
}
