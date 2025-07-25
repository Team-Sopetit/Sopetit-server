package com.soptie.server.domain.memberroutine;

import java.time.LocalDate;
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
	private boolean isAchieved = false;
	private boolean isAchievedToday = false;
	private int achievementCount = 0;
	private long memberId;
	private Long routineId;
	private LocalDate createdAt;
	private LocalDate updatedAt;
	private String content;
	private Long themeId;
	private LocalTime alarmTime;

	public MemberRoutine(Member member, Routine routine) {
		this.isAchieved = false;
		this.isAchievedToday = false;
		this.achievementCount = 0;
		this.memberId = member.getId();
		this.routineId = routine.getId();
	}

	public void achieve() {
		this.isAchievedToday = true;
		this.achievementCount += !this.isAchieved ? 1 : -1;
		this.isAchieved = !this.isAchieved;
	}

	public void cancel(LocalDate historyDate) {
		if (historyDate.equals(LocalDate.now())) {
			this.isAchievedToday = false;
			this.isAchieved = false;
		}
		this.achievementCount--;
	}
}
