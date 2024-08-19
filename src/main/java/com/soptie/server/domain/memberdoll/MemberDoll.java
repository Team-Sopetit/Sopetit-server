package com.soptie.server.domain.memberdoll;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberDoll {
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private DollCotton cottonInfo;
	private long memberId;
	private long dollId;
}
