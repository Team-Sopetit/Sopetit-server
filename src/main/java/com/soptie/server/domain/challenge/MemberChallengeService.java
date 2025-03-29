package com.soptie.server.domain.challenge;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.memberchallenge.CreateMemberChallengeRequest;
import com.soptie.server.api.controller.dto.response.memberchallenge.CreateMemberChallengeResponse;
import com.soptie.server.api.controller.dto.response.memberchallenge.MemberChallengeResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.challenge.util.ChallengeValidator;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;
import com.soptie.server.persistence.adapter.challenge.ChallengeAdapter;
import com.soptie.server.persistence.adapter.challenge.ChallengeHistoryAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberChallengeService {
	private final MemberChallengeAdapter memberChallengeAdapter;
	private final ThemeAdapter themeAdapter;
	private final ChallengeAdapter challengeAdapter;
	private final MemberAdapter memberAdapter;
	private final ChallengeHistoryAdapter challengeHistoryAdapter;
	private final ChallengeValidator challengeValidator;

	public Optional<MemberChallengeResponse> getMemberChallenge(long memberId) {
		val memberChallenge = memberChallengeAdapter.findByMember(memberId);
		return memberChallenge.map(this::toMemberChallenge);
	}

	private MemberChallengeResponse toMemberChallenge(MemberChallenge memberChallenge) {
		val challenge = challengeAdapter.findById(memberChallenge.getChallengeId());
		val theme = themeAdapter.findById(challenge.getThemeId());
		return MemberChallengeResponse.of(memberChallenge, theme, challenge);
	}

	@Transactional
	public CreateMemberChallengeResponse createMemberChallenge(long memberId, CreateMemberChallengeRequest request) {
		challengeValidator.validChallenge(memberId, request.challengeId());
		val member = memberAdapter.findById(memberId);
		val challenge = challengeAdapter.findById(request.challengeId());
		val savedMemberChallenge = memberChallengeAdapter.save(member, challenge);
		return CreateMemberChallengeResponse.of(savedMemberChallenge);
	}

	@Transactional
	public void deleteMemberChallenge(long memberId) {
		val memberChallenge = memberChallengeAdapter.findByMember(memberId)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "챌린지가 없는 회원, Member ID: " + memberId));
		memberChallengeAdapter.delete(memberChallenge);
	}

	@Transactional
	public void achieveMemberChallenge(long memberId) {
		val memberChallenge = memberChallengeAdapter.findByMember(memberId)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "챌린지가 없는 회원, Member ID: " + memberId));
		val member = memberAdapter.findById(memberId);
		member.getCottonInfo().addRainbowCottonCount();
		memberAdapter.update(member);
		memberChallenge.achieve();
		memberChallengeAdapter.update(memberChallenge);
		val challenge = challengeAdapter.findById(memberChallenge.getChallengeId());
		challengeHistoryAdapter.save(memberChallenge, challenge);
	}

	@Transactional
	public void deleteHistory(long historyId) {
		val history = challengeHistoryAdapter.findById(historyId);
		val memberChallenge = memberChallengeAdapter.findById(history.getMemberChallengeId());
		memberChallenge.cancel();
		memberChallengeAdapter.update(memberChallenge);
		challengeHistoryAdapter.deleteById(historyId);
	}
}
