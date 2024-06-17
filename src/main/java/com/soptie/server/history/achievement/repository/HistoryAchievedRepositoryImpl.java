package com.soptie.server.history.achievement.repository;

import static com.soptie.server.history.achievement.entity.QHistoryAchieved.*;

import java.time.LocalDate;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.history.achievement.entity.AchieveType;
import com.soptie.server.memberRoutine.entity.MemberRoutine;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Repository
@RequiredArgsConstructor
public class HistoryAchievedRepositoryImpl implements HistoryAchievedCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public boolean isAchievedToday(MemberRoutine memberRoutine) {
		val today = LocalDate.now().atStartOfDay();
		return queryFactory
				.from(historyAchieved)
				.where(
						historyAchieved.achievedAt.between(today, today.plusDays(1)),
						historyAchieved.member.eq(memberRoutine.getMember()),
						historyAchieved.routineId.eq(memberRoutine.getRoutineId()),
						historyAchieved.routineType.eq(memberRoutine.getType()),
						historyAchieved.achieveType.eq(AchieveType.ACHIEVE)
				)
				.select(historyAchieved.id)
				.fetchFirst() != null;
	}
}
