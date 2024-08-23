package com.soptie.server.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class Social {
	@NotNull
	protected SocialType socialType;
	@NotNull
	protected String socialId;
}
