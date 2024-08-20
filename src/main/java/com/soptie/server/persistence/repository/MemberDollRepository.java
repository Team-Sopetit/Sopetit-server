package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemberDollEntity;

public interface MemberDollRepository extends JpaRepository<MemberDollEntity, Long> {
	boolean existsByMemberId(long memberId);

	Optional<MemberDollEntity> findByMemberId(long memberId);
}
