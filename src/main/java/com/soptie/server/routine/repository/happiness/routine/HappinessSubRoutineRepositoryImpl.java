package com.soptie.server.routine.repository.happiness.routine;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soptie.server.routine.entity.happiness.QHappinessSubRoutine.happinessSubRoutine;
import static java.util.Objects.nonNull;

@Repository
@RequiredArgsConstructor
public class HappinessSubRoutineRepositoryImpl implements HappinessSubRoutineCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HappinessSubRoutine> findAllByRoutineId(Long routineId) {
        return queryFactory
                .selectFrom(happinessSubRoutine)
                .where(routineIdEq(routineId))
                .fetch();
    }

    private BooleanExpression routineIdEq(Long routineId) {
        return nonNull(routineId) ? happinessSubRoutine.routine.id.eq(routineId): null;
    }
}
