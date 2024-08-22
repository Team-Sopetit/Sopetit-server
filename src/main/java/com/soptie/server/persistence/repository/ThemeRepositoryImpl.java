package com.soptie.server.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.api.controller.dto.response.maker.MakerThemeResponse;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ThemeRepositoryImpl implements ThemeCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<MakerThemeResponse> findAllWithMaker() {
		return new ArrayList<>();
		// return queryFactory.select(new QMakerThemeResponse(theme, maker))
		// 	.from(theme)
		// 	.leftJoin(maker).on(theme.makerId.eq(maker.id))
		// 	.fetchJoin()
		// 	.fetch();
	}
}
