package com.soptie.server.memberRoutine.entity.happiness;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.happiness.HappinessSubRoutine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
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

	public MemberHappinessRoutine(Long id, Member member, HappinessSubRoutine routine) {
		this.id = id;
		this.member = member;
		this.routine = routine;
	}

	public MemberHappinessRoutine(Long id, Member member) {
		this.member = member;
		this.id = id;
	}

	public MemberHappinessRoutine(Member member, HappinessSubRoutine routine) {
		setMember(member);
		this.routine = routine;
	}

	private void setMember(Member member) {
		if (Objects.nonNull(this.member)) {
			this.member.resetHappinessRoutine();
		}
		this.member = member;
		member.addHappinessRoutine(this);
	}
}
