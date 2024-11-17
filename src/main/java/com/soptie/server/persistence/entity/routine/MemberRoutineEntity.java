package com.soptie.server.persistence.entity.routine;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.routine.Routine;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE softie.member_routine SET is_deleted = true WHERE id = ?")
@Table(
	name = "member_routine",
	schema = "softie",
	uniqueConstraints = @UniqueConstraint(columnNames = {"member_id", "routine_id"}))
public class MemberRoutineEntity extends BaseEntity {
	@Column(nullable = false)
	private boolean isAchieved;
	@Column(nullable = false)
	private boolean isAchievedToday;
	@Column(nullable = false)
	private int achievementCount;
	@Column(nullable = false)
	private boolean isDeleted;
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long routineId;

	//TODO: 아래 생성자 활용 (Entity 보호)
	public MemberRoutineEntity(Member member, Routine routine) {
		this.isAchieved = false;
		this.isAchievedToday = false;
		this.achievementCount = 0;
		this.isDeleted = false;
		this.memberId = member.getId();
		this.routineId = routine.getId();
	}

	public MemberRoutineEntity(MemberRoutine memberRoutine) {
		this.isAchieved = memberRoutine.isAchieved();
		this.isAchievedToday = memberRoutine.isAchievedToday();
		this.achievementCount = memberRoutine.getAchievementCount();
		this.isDeleted = false;
		this.memberId = memberRoutine.getMemberId();
		this.routineId = memberRoutine.getRoutineId();
	}

	public MemberRoutine toDomain() {
		return MemberRoutine.builder()
			.id(this.id)
			.isAchieved(this.isAchieved)
			.isAchievedToday(this.isAchievedToday)
			.achievementCount(this.achievementCount)
			.memberId(this.memberId)
			.routineId(this.routineId)
			.build();
	}

	public void update(MemberRoutine memberRoutine) {
		this.isAchieved = memberRoutine.isAchieved();
		this.isAchievedToday = memberRoutine.isAchievedToday();
		this.achievementCount = memberRoutine.getAchievementCount();
	}

	public void restore() {
		this.isDeleted = false;
	}
}
