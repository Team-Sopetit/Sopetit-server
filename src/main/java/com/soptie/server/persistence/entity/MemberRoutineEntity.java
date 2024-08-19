package com.soptie.server.persistence.entity;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.soptie.server.domain.memberroutine.MemberRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@SQLRestriction("is_deleted = false")
@SQLDelete(sql = "UPDATE softie.member_routine SET is_deleted = true WHERE id = ?")
@Table(name = "member_routine", schema = "softie")
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
}
