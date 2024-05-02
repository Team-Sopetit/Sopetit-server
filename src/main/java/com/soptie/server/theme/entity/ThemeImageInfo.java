package com.soptie.server.theme.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class ThemeImageInfo {

	private String iconImageUrl;

	private String backgroundImageUrl;

	private String dailyBackgroundImageUrl;

	public ThemeImageInfo(String iconImageUrl, String backgroundImageUrl, String dailyBackgroundImageUrl) {
		this.iconImageUrl = iconImageUrl;
		this.backgroundImageUrl = backgroundImageUrl;
		this.dailyBackgroundImageUrl = dailyBackgroundImageUrl;
	}
}
