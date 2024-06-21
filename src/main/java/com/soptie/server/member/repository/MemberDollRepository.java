package com.soptie.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.member.entity.MemberDoll;

public interface MemberDollRepository extends JpaRepository<MemberDoll, Long> {
}
