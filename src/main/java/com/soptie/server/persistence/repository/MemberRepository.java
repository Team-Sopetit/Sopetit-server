package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.entity.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

	Optional<Member> findByRefreshToken(String refreshToken);
}
