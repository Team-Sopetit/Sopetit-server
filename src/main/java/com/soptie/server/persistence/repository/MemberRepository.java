package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
	Optional<MemberEntity> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

	Optional<MemberEntity> findByRefreshToken(String refreshToken);
}
