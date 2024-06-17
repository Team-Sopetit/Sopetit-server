package com.soptie.server.history.achievement.entity;

import static jakarta.persistence.EnumType.*;

import java.time.LocalDateTime;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberRoutine.entity.MemberRoutine;
import com.soptie.server.routine.entity.RoutineType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HistoryAchieved {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	private LocalDateTime achievedAt;

	@Enumerated(value = STRING)
	private AchieveType achieveType;

	@Enumerated(value = STRING)
	private RoutineType routineType;

	private long routineId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	public HistoryAchieved(MemberRoutine memberRoutine) {
		this.achievedAt = LocalDateTime.now();
		this.achieveType = AchieveType.getType(memberRoutine);
		this.routineType = memberRoutine.getType();
		this.routineId = memberRoutine.getRoutineId();
		this.member = memberRoutine.getMember();
	}
}
