package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.MakerEntity;

public interface MakerRepository extends JpaRepository<MakerEntity, Long> {
}
