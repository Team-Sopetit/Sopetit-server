package com.soptie.server.api.controller.dto.response.maker;

import com.querydsl.core.annotations.QueryProjection;
import com.soptie.server.domain.maker.Maker;
import com.soptie.server.domain.theme.Theme;
import com.soptie.server.persistence.entity.MakerEntity;
import com.soptie.server.persistence.entity.ThemeEntity;

import jakarta.validation.constraints.NotNull;

public record MakerThemeResponse(
	@NotNull Theme theme,
	@NotNull Maker maker
) {

	@QueryProjection
	public MakerThemeResponse(ThemeEntity themeEntity, MakerEntity makerEntity) {
		this(
			themeEntity.toDomain(),
			makerEntity.toDomain()
		);
	}
}
