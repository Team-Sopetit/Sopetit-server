package com.soptie.server.domain.member;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class Social {
	@NotNull
	protected SocialType socialType;

	@NotNull
	protected String socialId;
}
