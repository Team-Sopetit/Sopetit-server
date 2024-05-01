package com.soptie.server.memberRoutine.entity;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import java.time.LocalDate;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.Routine;
import com.soptie.server.routine.entity.RoutineType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MemberRoutine {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_routine_id")
	private Long id;

	private boolean isAchieve;

	private int achieveCount;

	@Enumerated(value = STRING)
	private RoutineType type;

	private long routineId;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public MemberRoutine(Member member, Routine routine) {
		this.isAchieve = false;
		this.achieveCount = 0;
		this.type = routine.getType();
		this.routineId = routine.getId();
		this.member = member;
	}

	public MemberRoutine(DeletedMemberRoutine deletedMemberRoutine) {
		this.isAchieve = isAchievedToday(deletedMemberRoutine);
		this.achieveCount = deletedMemberRoutine.getAchieveCount();
		this.type = deletedMemberRoutine.getType();
		this.routineId = deletedMemberRoutine.getRoutineId();
		this.member = deletedMemberRoutine.getMember();
	}

	public MemberRoutine(Long id, boolean isAchieve, int achieveCount, RoutineType type, long routineId, Member member) {
		this.id = id;
		this.isAchieve = isAchieve;
		this.achieveCount = achieveCount;
		this.type = type;
		this.routineId = routineId;
		this.member = member;
	}

	public void achieve() {
		this.isAchieve = true;
		this.achieveCount++;
	}

	public void initAchieve() {
		this.isAchieve = false;
	}

	private boolean isAchievedToday(DeletedMemberRoutine deletedMemberRoutine) {
		return deletedMemberRoutine.isAchieve() && deletedMemberRoutine.getCreatedAt().equals(LocalDate.now());
	}
}
