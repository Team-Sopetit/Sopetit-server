package com.soptie.server.memberRoutine.entity;

import static jakarta.persistence.EnumType.*;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.RoutineType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Enumerated;
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
public class DeletedMemberRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "deleted_member_routine_id")
	private Long id;

	private int achieveCount;

	private boolean isAchieve;

	@Enumerated(value = STRING)
	private RoutineType type;

	private long routineId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	@CreatedDate
	private LocalDate createdAt;

	public DeletedMemberRoutine(MemberRoutine memberRoutine) {
		this.achieveCount = memberRoutine.getAchieveCount();
		this.isAchieve = memberRoutine.isAchieve();
		this.type = memberRoutine.getType();
		this.routineId = memberRoutine.getRoutineId();
		this.member = memberRoutine.getMember();
	}
}
