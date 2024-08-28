package com.soptie.server.domain.doll;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Doll {
	Long id;
	@NotNull
	DollType type;
}
