package com.soptie.server.routine.repository.happiness.routine;


import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soptie.server.routine.entity.happiness.QHappinessRoutine.happinessRoutine;

@Repository
@RequiredArgsConstructor
public class HappinessRoutineRepositoryImpl implements HappinessRoutineCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HappinessRoutine> findAllByThemes(List<Long> themeIds) {
        return queryFactory
                .selectFrom(happinessRoutine)
                .where(happinessRoutine.theme.id.in(themeIds))
                .orderBy(happinessRoutine.title.asc())
                .fetch();
    }
}
