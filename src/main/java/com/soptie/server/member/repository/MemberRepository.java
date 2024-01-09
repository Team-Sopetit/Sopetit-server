package com.soptie.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
