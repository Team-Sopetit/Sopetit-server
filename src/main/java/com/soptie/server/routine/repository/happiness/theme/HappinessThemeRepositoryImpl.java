package com.soptie.server.routine.repository.happiness.theme;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.happiness.HappinessTheme;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.soptie.server.routine.entity.happiness.QHappinessTheme.happinessTheme;


@Repository
@RequiredArgsConstructor
public class HappinessThemeRepositoryImpl implements HappinessThemeCustomRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<HappinessTheme> findAllOrderByNameAsc() {
        return queryFactory
                .selectFrom(happinessTheme)
                .orderBy(happinessTheme.name.asc())
                .fetch();
    }
}
