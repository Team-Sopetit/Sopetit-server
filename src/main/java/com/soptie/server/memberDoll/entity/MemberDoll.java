package com.soptie.server.memberDoll.entity;

import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.member.entity.Member;

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
}
