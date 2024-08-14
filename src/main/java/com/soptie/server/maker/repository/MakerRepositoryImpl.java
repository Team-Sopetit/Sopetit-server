package com.soptie.server.maker.repository;

import static com.soptie.server.maker.entity.QMaker.maker;
import static com.soptie.server.theme.entity.QTheme.theme;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.maker.repository.dto.MakerThemeResponse;
import com.soptie.server.maker.repository.dto.QMakerThemeResponse;

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
