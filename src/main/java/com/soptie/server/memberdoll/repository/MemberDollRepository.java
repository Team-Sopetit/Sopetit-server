package com.soptie.server.memberdoll.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.memberdoll.entity.MemberDoll;

public interface MemberDollRepository extends JpaRepository<MemberDoll, Long> {
}
