package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MemoEntity;

public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
}
