package com.soptie.server.persistence.repository.challenge;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.challenge.MemberChallengeEntity;

public interface MemberChallengeRepository extends JpaRepository<MemberChallengeEntity, Long> {
	Optional<MemberChallengeEntity> findByMemberIdAndAchievedIsFalse(long memberId);

	Optional<MemberChallengeEntity> findByMemberIdAndChallengeIdAndAchievedTrue(long memberId, long challengeId);

	void deleteByMemberId(long memberId);

	List<MemberChallengeEntity> findByMemberId(long memberId);

	List<MemberChallengeEntity> findByMemberIdAndChallengeIdIn(long memberId, List<Long> challengeIds);

	List<MemberChallengeEntity> findByIdIn(List<Long> ids);
}
