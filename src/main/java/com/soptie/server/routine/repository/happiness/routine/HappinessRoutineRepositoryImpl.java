package com.soptie.server.routine.repository.happiness.routine;


import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soptie.server.routine.entity.happiness.QHappinessRoutine.happinessRoutine;
import static com.soptie.server.routine.entity.happiness.QHappinessTheme.happinessTheme;
import static java.util.Objects.*;

@Repository
@RequiredArgsConstructor
public class HappinessRoutineRepositoryImpl implements HappinessRoutineCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HappinessRoutine> findAllByThemeId(Long themeId) {
        val titleInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", happinessRoutine.title);
        return queryFactory
                .selectFrom(happinessRoutine)
                .where(themeIdEq(themeId))
                .leftJoin(happinessRoutine.theme, happinessTheme).fetchJoin()
                .orderBy(titleInKRExpression.asc())
                .fetch();
    }

    private BooleanExpression themeIdEq(Long themeId) {
        return nonNull(themeId) ? happinessRoutine.theme.id.eq(themeId): null;
    }
}
