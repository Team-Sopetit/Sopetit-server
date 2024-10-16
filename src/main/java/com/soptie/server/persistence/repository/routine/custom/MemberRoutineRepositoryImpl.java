package com.soptie.server.persistence.repository.routine.custom;

import static com.soptie.server.persistence.entity.QMemberRoutineEntity.*;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberRoutineRepositoryImpl implements MemberRoutineCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public void bulkInitAchievement() {
		queryFactory.update(memberRoutineEntity)
			.set(memberRoutineEntity.isAchieved, false)
			.set(memberRoutineEntity.isAchievedToday, false)
			.where(memberRoutineEntity.isAchieved.isTrue())
			.execute();
	}

	@Override
	public long countByAchieved(boolean isAchieved) {
		return queryFactory
			.selectFrom(memberRoutineEntity)
			.where(memberRoutineEntity.isAchieved.eq(isAchieved))
			.stream().count();
	}
}
