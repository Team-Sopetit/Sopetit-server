package com.soptie.server.persistence.repository.routine.custom;

import static com.soptie.server.persistence.entity.routine.QMemberRoutineEntity.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRoutineRepositoryImpl implements MemberRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public void bulkInitAchievement() {
		LocalDate today = LocalDate.now();
		LocalDateTime startOfDay = today.atStartOfDay();
		LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

		queryFactory.update(memberRoutineEntity)
			.set(memberRoutineEntity.isAchieved, false)
			.where(
				memberRoutineEntity.isAchieved.isTrue()
					.and(memberRoutineEntity.lastAchievedAt
						.notBetween(startOfDay, endOfDay.minusNanos(1))
						.or(memberRoutineEntity.lastAchievedAt.isNull()))
			)
			.execute();
	}
}
