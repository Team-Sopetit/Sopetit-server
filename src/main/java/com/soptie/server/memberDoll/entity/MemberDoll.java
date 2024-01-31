package com.soptie.server.memberDoll.entity;

import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.member.entity.Member;

import com.soptie.server.memberDoll.exception.MemberDollException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.soptie.server.common.config.ValueConfig.*;
import static com.soptie.server.doll.message.ErrorCode.*;

@Entity
@NoArgsConstructor
@Getter
public class MemberDoll extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_doll_id")
	private Long id;

	private String name;

	private int happinessCottonCount;

	@OneToOne(mappedBy = "memberDoll")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "doll_id")
	private Doll doll;

	public MemberDoll(Member member, Doll doll, String name) {
		this.happinessCottonCount = 0;
		setMember(member);
		this.doll = doll;
		setName(name);
	}

	private void setMember(Member member) {
		this.member = member;
		member.setMemberDoll(this);
	}

	private void setName(String name) {
		if (!name.matches(MEMBER_DOLL_CONDITION)) {
			throw new MemberDollException(INVALID_NAME);
		}
		this.name = name;
	}

	public void addHappinessCottonCount() {
		this.happinessCottonCount++;
	}
}
