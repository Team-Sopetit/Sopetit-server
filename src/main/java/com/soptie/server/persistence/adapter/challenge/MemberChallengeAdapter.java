package com.soptie.server.persistence.adapter.challenge;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.challenge.MemberChallenge;
import com.soptie.server.domain.member.Member;
import com.soptie.server.persistence.entity.challenge.MemberChallengeEntity;
import com.soptie.server.persistence.repository.challenge.MemberChallengeRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberChallengeAdapter {
	private final MemberChallengeRepository memberChallengeRepository;

	public Optional<MemberChallenge> findByMember(long memberId) {
		return memberChallengeRepository.findByMemberIdAndAchievedIsFalse(memberId)
			.map(MemberChallengeEntity::toDomain);
	}

	public MemberChallenge save(Member member, Challenge challenge) {
		val memberChallenge = memberChallengeRepository
			.findByMemberIdAndChallengeIdAndAchievedTrue(member.getId(), challenge.getId());
		return memberChallenge
			// 추가한 이력이 있는 경우
			.map(entity -> entity.restore().toDomain())
			// 처음 추가하는 경우
			.orElseGet(() -> memberChallengeRepository.save(new MemberChallengeEntity(member, challenge)).toDomain());
	}

	public void delete(MemberChallenge memberChallenge) {
		val memberChallengeEntity = find(memberChallenge.getId());
		memberChallengeEntity.delete();
	}

	public void deleteAllByMemberId(long memberId) {
		memberChallengeRepository.deleteByMemberId(memberId);
	}

	public void update(MemberChallenge memberChallenge) {
		val memberChallengeEntity = find(memberChallenge.getId());
		memberChallengeEntity.update(memberChallenge);
	}

	public List<MemberChallenge> findAllByMemberId(long memberId) {
		return memberChallengeRepository.findByMemberId(memberId).stream()
			.map(MemberChallengeEntity::toDomain)
			.toList();
	}

	private MemberChallengeEntity find(long id) {
		return memberChallengeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "MemberChallenge ID: " + id));
	}
}
