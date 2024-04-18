package com.soptie.server.support.fixture;

import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;

public class HappinessSubRoutineFixture {

    private Long id;
    private String content;
    private String detailContent;
    private String timeTaken;
    private String place;

    private HappinessRoutine routine;

    private HappinessSubRoutineFixture() {
    }

    public static HappinessSubRoutineFixture happinessRoutine() {
        return new HappinessSubRoutineFixture();
    }

    public HappinessSubRoutineFixture id(Long id) {
        this.id = id;
        return this;
    }

    public HappinessSubRoutineFixture content(String content) {
        this.content = content;
        return this;
    }

    public HappinessSubRoutineFixture detailContent(String detailContent) {
        this.detailContent = detailContent;
        return this;
    }

    public HappinessSubRoutineFixture timeTaken(String timeTaken) {
        this.timeTaken = timeTaken;
        return this;
    }

    public HappinessSubRoutineFixture place(String place) {
        this.place = place;
        return this;
    }

    public HappinessSubRoutineFixture routine(HappinessRoutine routine) {
        this.routine = routine;
        return this;
    }

    public HappinessSubRoutine build() {
        return new HappinessSubRoutine(id, content, detailContent, timeTaken, place, routine);
    }

}
