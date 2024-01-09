package com.soptie.server.routine.repository.daily.routine;

import static com.soptie.server.routine.entity.daily.QDailyRoutine.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.daily.DailyRoutine;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DailyRoutineRepositoryImpl implements DailyRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<DailyRoutine> findAllByThemes(List<Long> themeIds) {
		return queryFactory
			.selectFrom(dailyRoutine)
			.where(dailyRoutine.theme.id.in(themeIds))
			.orderBy(dailyRoutine.content.asc())
			.fetch();
	}
}
