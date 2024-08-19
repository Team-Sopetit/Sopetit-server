package com.soptie.server.domain.doll;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class Doll {
	@NotNull
	DollType type;
	@NotNull
	DollImageUrls imageUrls;
}
