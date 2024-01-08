package com.soptie.server.routine.repository.daily.theme;

import static com.soptie.server.routine.entity.daily.QDailyTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.routine.entity.daily.DailyTheme;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DailyThemeRepositoryImpl implements DailyThemeCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<DailyTheme> findAllOrderByNameAsc() {
		return queryFactory
			.selectFrom(dailyTheme)
			.orderBy(dailyTheme.name.asc())
			.fetch();
	}
}
