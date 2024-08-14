package com.soptie.server.maker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.maker.entity.Maker;

public interface MakerRepository extends JpaRepository<Maker, Long>, MakerCustomRepository {
}
