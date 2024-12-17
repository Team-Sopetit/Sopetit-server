package com.soptie.server.domain.challenge.util;

import org.springframework.stereotype.Component;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChallengeValidator {

	private final MemberChallengeAdapter memberChallengeAdapter;

	public void checkChallengeNotExists(long memberId) {
		if (memberChallengeAdapter.findByMember(memberId).isPresent()) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "챌린지가 존재하는 회원");
		}
	}
}
