package com.soptie.server.routine.repository.daily.routine;

import static com.soptie.server.routine.entity.daily.QDailyRoutine.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class DailyRoutineRepositoryImpl implements DailyRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<DailyRoutine> findAllByThemes(List<Long> themeIds) {
		val contentInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", dailyRoutine.content);
		return queryFactory
			.selectFrom(dailyRoutine)
			.where(dailyRoutine.theme.id.in(themeIds))
			.orderBy(contentInKRExpression.asc())
			.fetch();
	}

	@Override
	public List<DailyRoutine> findAllByTheme(DailyTheme theme) {
		val contentInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", dailyRoutine.content);
		return queryFactory
				.selectFrom(dailyRoutine)
				.where(dailyRoutine.theme.eq(theme))
				.orderBy(contentInKRExpression.asc())
				.fetch();
	}
}
