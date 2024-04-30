package com.soptie.server.memberRoutine.entity;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import com.soptie.server.member.entity.Member;
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

	public MemberRoutine(Long id, boolean isAchieve, int achieveCount, RoutineType type, long routineId, Member member) {
		this.id = id;
		this.isAchieve = isAchieve;
		this.achieveCount = achieveCount;
		this.type = type;
		this.routineId = routineId;
		this.member = member;
	}
}
