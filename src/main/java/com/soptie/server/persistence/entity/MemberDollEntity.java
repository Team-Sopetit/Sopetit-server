package com.soptie.server.persistence.entity;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.domain.memberdoll.DollCotton;
import com.soptie.server.domain.memberdoll.MemberDoll;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private DollType dollType;
	@Column(nullable = false)
	private int basicCottonCount;
	@Column(nullable = false)
	private int rainbowCottonCount;
	@Column(nullable = false, unique = true)
	private long memberId;
	@Column(nullable = false)
	private long dollId;

	public MemberDollEntity(MemberDoll memberDoll) {
		this.name = memberDoll.getName();
		this.dollType = memberDoll.getDollType();
		this.basicCottonCount = 0;
		this.rainbowCottonCount = 0;
		this.memberId = memberDoll.getMemberId();
		this.dollId = memberDoll.getDollId();
	}

	public MemberDoll toDomain() {
		return MemberDoll.builder()
			.id(this.id)
			.dollType(this.dollType)
			.name(this.name)
			.cottonInfo(toCottonInfo())
			.memberId(memberId)
			.dollId(dollId)
			.build();
	}

	public void update(MemberDoll memberDoll) {
		this.name = memberDoll.getName();
		this.basicCottonCount = memberDoll.getCottonInfo().getBasicCottonCount();
		this.rainbowCottonCount = memberDoll.getCottonInfo().getRainbowCottonCount();
	}

	private DollCotton toCottonInfo() {
		return DollCotton.builder()
			.basicCottonCount(this.basicCottonCount)
			.rainbowCottonCount(this.rainbowCottonCount)
			.build();
	}
}
