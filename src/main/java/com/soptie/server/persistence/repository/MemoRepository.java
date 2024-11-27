package com.soptie.server.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
	Optional<MemoEntity> findByIdAndMemberId(long id, long memberId);

	void deleteByIdAndMemberId(long id, long memberId);
}
