package com.soptie.server.memberRoutine.entity.daily;

import java.util.Objects;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.daily.DailyRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class MemberDailyRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_routine_id")
	private Long id;

	private int achieveCount;

	private boolean isAchieve;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routine_id")
	private DailyRoutine routine;

	public MemberDailyRoutine(Member member, DailyRoutine routine) {
		this.achieveCount = 0;
		this.isAchieve = false;
		setMember(member);
		this.routine = routine;
	}

	public MemberDailyRoutine(Long id, Member member, DailyRoutine routine, boolean isAchieve, int achieveCount) {
		this.id = id;
		this.member = member;
		this.routine = routine;
		this.isAchieve = isAchieve;
		this.achieveCount = achieveCount;
	}

	public MemberDailyRoutine(Member member, DailyRoutine routine, int achieveCount) {
		this.achieveCount = achieveCount;
		this.isAchieve = false;
		setMember(member);
		this.routine = routine;
	}

	public MemberDailyRoutine(Member member, DailyRoutine routine, int achieveCount, boolean isAchieve) {
		this.achieveCount = achieveCount;
		this.isAchieve = isAchieve;
		setMember(member);
		this.routine = routine;
	}

	private void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			this.member.getDailyRoutines().remove(this);
		}
		this.member = member;
		member.getDailyRoutines().add(this);
	}

	public void achieveRoutine() {
		this.isAchieve = true;
		this.achieveCount++;
		this.member.addDailyCotton();
	}

	public void initAchievement() {
		this.isAchieve = false;
	}
}
