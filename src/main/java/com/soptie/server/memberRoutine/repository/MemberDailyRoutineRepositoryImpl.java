package com.soptie.server.memberRoutine.repository;

import static com.soptie.server.memberRoutine.entity.daily.QMemberDailyRoutine.*;
import static com.soptie.server.routine.entity.daily.QDailyRoutine.*;
import static com.soptie.server.routine.entity.daily.QDailyTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class MemberDailyRoutineRepositoryImpl implements MemberDailyRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MemberDailyRoutine> findAllByMember(Member member) {
		val contentInKRExpression = Expressions.stringTemplate("SUBSTR({0}, 1, 1)", memberDailyRoutine.routine.content);
		return queryFactory
			.selectFrom(memberDailyRoutine)
			.where(memberDailyRoutine.member.eq(member))
			.leftJoin(memberDailyRoutine.routine, dailyRoutine).fetchJoin()
			.leftJoin(dailyRoutine.theme, dailyTheme).fetchJoin()
			.orderBy(
				memberDailyRoutine.achieveCount.desc(),
				contentInKRExpression.asc()
			)
			.fetch();
	}
}
