package com.soptie.server.support;

import com.soptie.server.routine.entity.daily.DailyTheme;

public class DailyThemeFixture {

	private Long id;
	private String name;
	private String iconImageUrl;
	private String backgroundImageUrl;

	private DailyThemeFixture() {
	}

	public static DailyThemeFixture dailyTheme() {
		return new DailyThemeFixture();
	}

	public DailyThemeFixture id(Long id) {
		this.id = id;
		return this;
	}

	public DailyThemeFixture name(String name) {
		this.name = name;
		return this;
	}

	public DailyThemeFixture imageUrl(String imageUrl) {
		this.iconImageUrl = imageUrl;
		this.backgroundImageUrl = imageUrl;
		return this;
	}

	public DailyTheme build() {
		return new DailyTheme(id, name, iconImageUrl, backgroundImageUrl);
	}
}
