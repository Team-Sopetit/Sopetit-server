package com.soptie.server.memberRoutine.entity.daily;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.daily.DailyRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@EntityListeners(AuditingEntityListener.class)
public class CompletedMemberDailyRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_routine_id")
	private Long id;

	private int achieveCount;

	private Boolean isAchieve;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routine_id")
	private DailyRoutine routine;

	@CreatedDate
	protected LocalDateTime createdAt;

	public CompletedMemberDailyRoutine(MemberDailyRoutine routine) {
		this.achieveCount = routine.getAchieveCount();
		this.isAchieve = routine.isAchieve();
		setMember(routine);
		this.routine = routine.getRoutine();
	}

	public CompletedMemberDailyRoutine(Long id, Member member, DailyRoutine dailyRoutine, int achieveCount) {
		this.id = id;
		this.member = member;
		this.routine = dailyRoutine;
		this.achieveCount = achieveCount;
	}

	private void setMember(MemberDailyRoutine routine) {
		routine.getMember().getDailyRoutines().remove(routine);
		this.member = routine.getMember();
	}
}
