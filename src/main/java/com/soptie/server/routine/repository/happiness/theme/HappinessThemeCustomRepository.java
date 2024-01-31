package com.soptie.server.routine.repository.happiness.theme;

import com.soptie.server.routine.entity.happiness.HappinessTheme;

import java.util.List;

public interface HappinessThemeCustomRepository {
    List<HappinessTheme> findAllOrderByNameAsc();
}
