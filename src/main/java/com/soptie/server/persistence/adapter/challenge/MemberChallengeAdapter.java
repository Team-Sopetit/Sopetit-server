package com.soptie.server.persistence.adapter.challenge;

import java.util.List;
import java.util.Optional;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.membermission.MemberChallenge;
import com.soptie.server.persistence.entity.mission.MemberChallengeEntity;
import com.soptie.server.persistence.repository.mission.MemberChallengeRepository;

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
			// 달성한 이력이 있는 회원
			.map(MemberChallengeEntity::restore)
			// 처음 추가하는 회원
			.orElse(memberChallengeRepository.save(new MemberChallengeEntity(member, challenge)))
			.toDomain();
	}

	public void delete(MemberChallenge memberChallenge) {
		val memberChallengeEntity = find(memberChallenge.getId());
		memberChallengeEntity.delete();
	}

	public void deleteAllByMemberId(long memberId) {
		memberChallengeRepository.deleteAllByMemberId(memberId);
	}

	public void update(MemberChallenge memberChallenge) {
		val memberMissionEntity = find(memberChallenge.getId());
		memberMissionEntity.update(memberChallenge);
	}

	public void flush() {
		memberChallengeRepository.flush();
	}

	private MemberChallengeEntity restore(
		List<MemberChallengeEntity> deletedMemberMissions,
		Member member,
		Challenge challenge
	) {
		val deletedMemberMission = deletedMemberMissions.stream()
			.filter(mm -> mm.getMissionId() == challenge.getId())
			.findFirst()
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Member ID: " + member.getId() + " Mission ID: " + challenge.getId()));
		deletedMemberMission.restore();
		return deletedMemberMission;
	}

	private MemberChallengeEntity find(long id) {
		return memberChallengeRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "MemberMission ID: " + id));
	}
}
