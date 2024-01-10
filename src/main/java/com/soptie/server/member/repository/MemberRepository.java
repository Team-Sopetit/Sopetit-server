package com.soptie.server.member.repository;

<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
=======
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsBySocialTypeAndSocialId(SocialType socialType, String socialId);

    Optional<Member> findBySocialTypeAndSocialId(SocialType socialType, String socialId);
>>>>>>> develop
}
