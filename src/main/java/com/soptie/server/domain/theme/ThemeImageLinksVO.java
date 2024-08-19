package com.soptie.server.domain.theme;

import static lombok.AccessLevel.*;

import com.soptie.server.persistence.entity.ThemeImageLinks;

import lombok.Builder;

@Builder(access = PRIVATE)
public record ThemeImageLinksVO(
	String iconImageUrl,
	String backgroundImageUrl,
	String dailyCardImageUrl,
	String dailyIconImageUrl,
	String happinessCardImageUrl
) {

	public static ThemeImageLinksVO from(ThemeImageLinks imageLinks) {
		return ThemeImageLinksVO.builder()
			.iconImageUrl(imageLinks.getIconImageUrl())
			.backgroundImageUrl(imageLinks.getBackgroundImageUrl())
			.dailyCardImageUrl(imageLinks.getDailyCardImageUrl())
			.dailyIconImageUrl(imageLinks.getDailyIconImageUrl())
			.happinessCardImageUrl(imageLinks.getHappinessCardImageUrl())
			.build();
	}
}
