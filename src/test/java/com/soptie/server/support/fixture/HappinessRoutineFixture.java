package com.soptie.server.support.fixture;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;

public class HappinessRoutineFixture {

    private Long id;
    private String title;
    private HappinessTheme theme = HappinessThemeFixture.happinessTheme().build();

    private HappinessRoutineFixture() {
    }

    public static HappinessRoutineFixture happinessRoutine() {
        return new HappinessRoutineFixture();
    }

    public HappinessRoutineFixture id(Long id) {
        this.id = id;
        return this;
    }

    public HappinessRoutineFixture title(String title) {
        this.title = title;
        return this;
    }

    public HappinessRoutineFixture theme(HappinessTheme theme) {
        this.theme = theme;
        return this;
    }

    public HappinessRoutine build() {
        return new HappinessRoutine(id, title, theme);
    }
}
