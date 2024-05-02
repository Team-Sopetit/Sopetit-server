package com.soptie.server.theme.repository;

import static com.soptie.server.theme.entity.QTheme.*;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.soptie.server.common.support.ExpressionGenerator;
import com.soptie.server.theme.entity.Theme;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ThemeRepositoryImpl implements ThemeCustomRepository {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Theme> findAllOrderByNameAsc() {
		return queryFactory
				.selectFrom(theme)
				.orderBy(ExpressionGenerator.getFirstLetter(theme.name).asc())
				.fetch();
	}
}
