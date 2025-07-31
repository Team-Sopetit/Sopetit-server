package com.soptie.server.persistence.entity.routine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import com.soptie.server.common.utils.TimeUtils;
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
	private int achievementCount;

	@Column
	private LocalDateTime lastAchievedAt;

	@Column(nullable = false)
	private boolean isDeleted;
	@Column(nullable = false)
	private long memberId;
	private Long routineId;
	private String content;
	private LocalTime alarmTime;
	private Long themeId;

	public MemberRoutineEntity(Member member, Routine routine) {
		this.isAchieved = false;
		this.achievementCount = 0;
		this.isDeleted = false;
		this.memberId = member.getId();
		this.routineId = routine.getId();
		this.content = routine.getContent();
	}

	public MemberRoutineEntity(MemberRoutine memberRoutine) {
		this.isAchieved = memberRoutine.isAchieved();
		this.achievementCount = memberRoutine.getAchievementCount();
		this.lastAchievedAt = memberRoutine.getLastAchievedAt();
		this.isDeleted = false;
		this.memberId = memberRoutine.getMemberId();
		this.routineId = memberRoutine.getRoutineId();
		this.content = memberRoutine.getContent();
		this.alarmTime = memberRoutine.getAlarmTime();
		this.themeId = memberRoutine.getThemeId();
	}

	//todo. add converter
	public MemberRoutine toDomain() {
		return MemberRoutine.builder()
			.id(this.id)
			.isAchieved(isAchieved && LocalDate.now().equals(TimeUtils.toDateTime(lastAchievedAt)))
			.lastAchievedAt(this.lastAchievedAt)
			.achievementCount(this.achievementCount)
			.memberId(this.memberId)
			.routineId(this.routineId)
			.createdAt(this.createdAt.toLocalDate())
			.updatedAt(this.updatedAt.toLocalDate())
			.content(this.content)
			.themeId(this.themeId)
			.alarmTime(this.alarmTime)
			.build();
	}

	public void update(MemberRoutine memberRoutine) {
		this.isAchieved = memberRoutine.isAchieved();
		this.lastAchievedAt = memberRoutine.getLastAchievedAt();
		this.achievementCount = memberRoutine.getAchievementCount();
		this.content = memberRoutine.getContent();
	}

	public void updateAll(MemberRoutine memberRoutine) {
		this.isAchieved = memberRoutine.isAchieved();
		this.lastAchievedAt = memberRoutine.getLastAchievedAt();
		this.achievementCount = memberRoutine.getAchievementCount();
		this.memberId = memberRoutine.getMemberId();
		this.routineId = memberRoutine.getRoutineId();
		this.content = memberRoutine.getContent();
		this.alarmTime = memberRoutine.getAlarmTime();
		this.themeId = memberRoutine.getThemeId();
	}

	public void restore() {
		if (isAchieved && !isAchievedToday()) {
			this.isAchieved = false;
		}
		this.isDeleted = false;
	}

	private boolean isAchievedToday() {
		LocalDate achievedDate = Optional.ofNullable(lastAchievedAt).map(LocalDateTime::toLocalDate).orElse(null);
		return LocalDate.now().equals(achievedDate);
	}
}
