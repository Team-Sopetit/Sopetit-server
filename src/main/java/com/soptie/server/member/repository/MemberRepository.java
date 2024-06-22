package com.soptie.server.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;

public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);

	Optional<Member> findByRefreshToken(String refreshToken);
}
