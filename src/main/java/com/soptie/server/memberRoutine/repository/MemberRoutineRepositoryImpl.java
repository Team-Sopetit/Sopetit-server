package com.soptie.server.memberRoutine.repository;

import static com.soptie.server.memberRoutine.entity.QMemberRoutine.*;
import static com.soptie.server.routine.entity.QRoutine.*;
import static com.soptie.server.theme.entity.QTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.common.support.ExpressionGenerator;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.repository.dto.MemberRoutineResponse;
import com.soptie.server.memberRoutine.repository.dto.QMemberRoutineResponse;
import com.soptie.server.routine.entity.RoutineType;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRoutineRepositoryImpl implements MemberRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MemberRoutineResponse> findByTypeAndMember(RoutineType type, Member member) {
		return queryFactory
				.select(new QMemberRoutineResponse(memberRoutine, routine))
				.from(memberRoutine)
				.leftJoin(routine).on(memberRoutine.routineId.eq(routine.id))
				.leftJoin(routine.theme, theme).fetchJoin()
				.where(
						memberRoutine.type.eq(type),
						memberRoutine.member.eq(member)
				)
				.orderBy(
						memberRoutine.isAchieve.asc(),
						memberRoutine.achieveCount.desc(),
						ExpressionGenerator.getFirstLetter(routine.content).asc()
				)
				.fetch();
	}
}
