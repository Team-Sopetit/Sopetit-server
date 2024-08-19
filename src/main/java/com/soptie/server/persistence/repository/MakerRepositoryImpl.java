package com.soptie.server.persistence.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.api.controller.dto.response.maker.MakerThemeResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MakerRepositoryImpl implements MakerCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MakerThemeResponse> findAllWithTheme() {
		return queryFactory.select(new QMakerThemeResponse(maker, theme))
			.from(maker)
			.leftJoin(theme).on(maker.themeId.eq(theme.id))
			.fetchJoin()
			.fetch();
	}
}
