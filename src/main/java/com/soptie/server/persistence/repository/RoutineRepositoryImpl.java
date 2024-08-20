package com.soptie.server.persistence.repository;

import static java.util.Objects.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.common.support.ExpressionGenerator;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class RoutineRepositoryImpl implements RoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	public List<Routine> findByTypeAndThemeIds(RoutineType type, List<Long> themeIds) {
		return queryFactory
			.selectFrom(routine)
			.where(
				routine.type.eq(type),
				routine.theme.id.in(themeIds)
			)
			.orderBy(ExpressionGenerator.getFirstLetter(routine.content).asc())
			.fetch();
	}

	public List<Routine> findByTypeAndThemeAndNotMember(long memberId, RoutineType type, long themeId) {
		return queryFactory
			.selectFrom(routine)
			.leftJoin(memberRoutine)
			.on(
				routine.id.eq(memberRoutine.routineId),
				memberRoutine.type.eq(type),
				memberRoutine.member.id.eq(memberId)
			)
			.where(
				memberRoutine.isNull(),
				routine.type.eq(type),
				routine.theme.id.eq(themeId)
			)
			.orderBy(ExpressionGenerator.getFirstLetter(routine.content).asc())
			.fetch();
	}

	public List<Routine> findByTypeAndThemeId(RoutineType type, Long themeId) {
		return queryFactory
			.selectFrom(routine)
			.where(
				routine.type.eq(type),
				themeEq(themeId)
			)
			.orderBy(ExpressionGenerator.getFirstLetter(routine.content).asc())
			.fetch();
	}

	@Override
	public List<Routine> findByIdsAndType(List<Long> ids, RoutineType type) {
		return queryFactory
			.selectFrom(routine)
			.where(
				routine.id.in(ids),
				routine.type.eq(type)
			)
			.fetch();
	}

	private BooleanExpression themeEq(Long themeId) {
		return (isNull(themeId) || themeId == 0L) ? null : routine.theme.id.eq(themeId);
	}
}