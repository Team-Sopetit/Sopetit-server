package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemberDollEntity;

public interface MemberDollRepository extends JpaRepository<MemberDollEntity, Long> {
}
