package com.soptie.server.memberRoutine.entity;

import java.util.Objects;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class MemberHappinessRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_routine_id")
	private Long id;

	@OneToOne(mappedBy = "happinessRoutine")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routine_id")
	private HappinessSubRoutine routine;

	public MemberHappinessRoutine(Member member, HappinessSubRoutine routine) {
		setMember(member);
		this.routine = routine;
	}

	private void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			this.member.initHappinessRoutine();
		}
		this.member = member;
		member.addHappinessRoutine(this);
	}
}
