package com.soptie.server.domain.maker;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Maker {
	private Long id;
	@NotNull
	private String name;
	private String introductionUrl;
	private String profileImageUrl;
	@NotNull
	private Tags tags;
}
