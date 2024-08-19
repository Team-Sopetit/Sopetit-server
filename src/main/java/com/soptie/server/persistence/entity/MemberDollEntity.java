package com.soptie.server.persistence.entity;

import com.soptie.server.domain.memberdoll.DollCotton;
import com.soptie.server.domain.memberdoll.MemberDoll;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_doll", schema = "softie")
public class MemberDollEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private int basicCottonCount;
	@Column(nullable = false)
	private int rainbowCottonCount;
	@Column(nullable = false)
	private long memberId;
	@Column(nullable = false)
	private long dollId;

	public MemberDollEntity(MemberDoll memberDoll) {
		this.name = memberDoll.getName();
		this.basicCottonCount = 0;
		this.rainbowCottonCount = 0;
		this.memberId = memberDoll.getMemberId();
		this.dollId = memberDoll.getDollId();
	}

	public MemberDoll toDomain() {
		return MemberDoll.builder()
			.id(this.id)
			.name(this.name)
			.cottonInfo(toCottonInfo())
			.memberId(memberId)
			.dollId(dollId)
			.build();
	}

	private DollCotton toCottonInfo() {
		return DollCotton.builder()
			.basicCottonCount(this.basicCottonCount)
			.rainbowCottonCount(this.rainbowCottonCount)
			.build();
	}
}
