package com.soptie.server.routine.repository.daily.theme;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.routine.entity.daily.DailyTheme;

public interface DailyThemeRepository extends JpaRepository<DailyTheme, Long>, DailyThemeCustomRepository {
}
