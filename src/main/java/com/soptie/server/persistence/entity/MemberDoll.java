package com.soptie.server.persistence.entity;

import static com.soptie.server.config.ValueConfig.*;
import static com.soptie.server.common.message.DollErrorCode.*;

import com.soptie.server.common.exception.MemberDollException;

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

	public MemberDoll(Long id) {
		this.id = id;
	}

	public MemberDoll(Long id, String name, int happinessCottonCount, Doll doll) {
		this.id = id;
		this.name = name;
		this.happinessCottonCount = happinessCottonCount;
		this.doll = doll;
	}

	private void setMember(Member member) {
		this.member = member;
		member.registerMemberDoll(this);
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
