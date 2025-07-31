package com.soptie.server.domain.memberroutine;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.routine.Routine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class MemberRoutine {

	private Long id;
	private boolean isAchieved;
	private int achievementCount;
	private LocalDateTime lastAchievedAt;

	private long memberId;
	private Long routineId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private String content;
	private Long themeId;
	private LocalTime alarmTime;

	public MemberRoutine(Member member, Routine routine) {
		this.isAchieved = false;
		this.achievementCount = 0;
		this.memberId = member.getId();
		this.routineId = routine.getId();
	}
}
