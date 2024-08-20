package com.soptie.server.domain.memberdoll;

import com.soptie.server.domain.doll.DollType;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDoll {
	private Long id;
	@NotNull
	private DollType dollType;
	@NotNull
	private String name;
	@NotNull
	private DollCotton cottonInfo;
	private long memberId;
	private long dollId;

	public MemberDoll(String name, DollType dollType, long memberId, long dollId) {
		this.name = name;
		this.dollType = dollType;
		this.cottonInfo = new DollCotton(0, 0);
		this.memberId = memberId;
		this.dollId = dollId;
	}
}
