package com.soptie.server.persistence.repository;

import static com.soptie.server.persistence.entity.QMakerEntity.*;
import static com.soptie.server.persistence.entity.QThemeEntity.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.api.controller.dto.response.maker.MakerThemeResponse;
import com.soptie.server.api.controller.dto.response.maker.QMakerThemeResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ThemeRepositoryImpl implements ThemeCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MakerThemeResponse> findAllWithMaker() {
		return queryFactory.select(new QMakerThemeResponse(themeEntity, makerEntity))
			.from(themeEntity)
			.leftJoin(makerEntity).on(themeEntity.makerId.eq(makerEntity.id))
			.fetchJoin()
			.fetch();
	}
}
