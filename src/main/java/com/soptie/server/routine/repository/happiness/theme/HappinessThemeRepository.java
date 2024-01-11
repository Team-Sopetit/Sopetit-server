package com.soptie.server.routine.repository.happiness.theme;

import com.soptie.server.routine.entity.happiness.HappinessTheme;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HappinessThemeRepository extends JpaRepository<HappinessTheme, Long>, HappinessThemeCustomRepository {
}
