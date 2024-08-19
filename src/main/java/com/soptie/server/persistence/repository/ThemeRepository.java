package com.soptie.server.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>, ThemeCustomRepository {
}
