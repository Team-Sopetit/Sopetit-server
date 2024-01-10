package com.soptie.server.memberRoutine.repository;

import static com.soptie.server.memberRoutine.entity.daily.QMemberDailyRoutine.*;
import static com.soptie.server.routine.entity.daily.QDailyRoutine.*;
import static com.soptie.server.routine.entity.daily.QDailyTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberDailyRoutineRepositoryImpl implements MemberDailyRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MemberDailyRoutine> findAllByMember(Member member) {
		return queryFactory
			.selectFrom(memberDailyRoutine)
			.where(memberDailyRoutine.member.eq(member))
			.leftJoin(memberDailyRoutine.routine, dailyRoutine).fetchJoin().distinct()
			.leftJoin(dailyRoutine.theme, dailyTheme).fetchJoin().distinct()
			.fetch();
	}
}
