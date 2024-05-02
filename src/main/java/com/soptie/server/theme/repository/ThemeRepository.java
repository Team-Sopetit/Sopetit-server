package com.soptie.server.theme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.theme.entity.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Long>, ThemeCustomRepository {
}
