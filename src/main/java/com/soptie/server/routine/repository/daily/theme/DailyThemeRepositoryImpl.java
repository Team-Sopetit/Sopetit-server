package com.soptie.server.routine.repository.daily.theme;

import static com.soptie.server.routine.entity.daily.QDailyRoutine.*;
import static com.soptie.server.routine.entity.daily.QDailyTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class DailyThemeRepositoryImpl implements DailyThemeCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<DailyTheme> findAllOrderByNameAsc() {
		val nameInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", dailyTheme.name);
		return queryFactory
			.selectFrom(dailyTheme)
			.orderBy(nameInKRExpression.asc())
			.fetch();
	}
}
