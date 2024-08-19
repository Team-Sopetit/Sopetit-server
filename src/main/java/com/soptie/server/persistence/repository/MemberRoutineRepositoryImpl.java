package com.soptie.server.persistence.repository;

import static com.soptie.server.memberroutine.entity.QMemberRoutine.*;
import static com.soptie.server.routine.entity.QChallenge.*;
import static com.soptie.server.routine.entity.QRoutine.*;
import static com.soptie.server.persistence.entity.RoutineType.*;
import static com.soptie.server.theme.entity.QTheme.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.common.support.ExpressionGenerator;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.repository.dto.MemberChallengeResponse;
import com.soptie.server.persistence.repository.dto.MemberRoutineResponse;
import com.soptie.server.persistence.repository.dto.QMemberChallengeResponse;
import com.soptie.server.persistence.repository.dto.QMemberRoutineResponse;
import com.soptie.server.persistence.entity.RoutineType;

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

	@Override
	public Optional<MemberChallengeResponse> findChallengeByMember(Member member) {
		return Optional.ofNullable(queryFactory
			.select(new QMemberChallengeResponse(memberRoutine, challenge, theme))
			.from(memberRoutine)
			.leftJoin(challenge).on(memberRoutine.routineId.eq(challenge.id))
			.leftJoin(challenge.routine, routine).fetchJoin()
			.leftJoin(challenge.routine.theme, theme).fetchJoin()
			.where(
				memberRoutine.type.eq(CHALLENGE),
				memberRoutine.member.eq(member)
			)
			.fetchFirst()
		);
	}
}
