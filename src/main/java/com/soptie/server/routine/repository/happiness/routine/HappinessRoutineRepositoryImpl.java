package com.soptie.server.routine.repository.happiness.routine;


import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soptie.server.routine.entity.happiness.QHappinessRoutine.happinessRoutine;
import static com.soptie.server.routine.entity.happiness.QHappinessTheme.happinessTheme;

@Repository
@RequiredArgsConstructor
public class HappinessRoutineRepositoryImpl implements HappinessRoutineCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HappinessRoutine> findAllByTheme(HappinessTheme theme) {
        val titleInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", happinessRoutine.title);
        return queryFactory
                .selectFrom(happinessRoutine)
                .where(happinessRoutine.theme.eq(theme))
                .leftJoin(happinessRoutine.theme, happinessTheme).fetchJoin()
                .orderBy(titleInKRExpression.asc())
                .fetch();
    }
}
