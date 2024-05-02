package com.soptie.server.common.support;

import org.springframework.stereotype.Component;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.StringTemplate;

@Component
public class ExpressionGenerator {

	public static StringTemplate getFirstLetter(StringPath str) {
		return Expressions.stringTemplate("SUBSTR({0}, 1, 1)", str);
	}
}
