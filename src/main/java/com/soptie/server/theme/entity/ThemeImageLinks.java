package com.soptie.server.theme.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class ThemeImageLinks {

	private String iconImageUrl;

	private String backgroundImageUrl;

	private String dailyCardImageUrl;

	private String dailyIconImageUrl;

	private String happinessCardImageUrl;

	public ThemeImageLinks(String iconImageUrl, String backgroundImageUrl, String dailyCardImageUrl,
		String dailyIconImageUrl, String happinessCardImageUrl) {
		this.iconImageUrl = iconImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.dailyCardImageUrl = dailyCardImageUrl;
		this.dailyIconImageUrl = dailyIconImageUrl;
		this.happinessCardImageUrl = happinessCardImageUrl;
	}
}
