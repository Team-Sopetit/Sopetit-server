package com.soptie.server.support.fixture;

import com.soptie.server.theme.entity.ThemeImageInfo;
import com.soptie.server.theme.entity.Theme;

public class ThemeFixture {

	private Long id;
	private String name;
	private final ThemeImageInfo imageInfo = new ThemeImageInfo("https://...", "https://...");

	private ThemeFixture() {
	}

	public static ThemeFixture theme() {
		return new ThemeFixture();
	}

	public ThemeFixture id(Long id) {
		this.id = id;
		return this;
	}

	public ThemeFixture name(String name) {
		this.name = name;
		return this;
	}

	public Theme build() {
		return Theme.builder()
				.id(this.id)
				.name(this.name)
				.imageInfo(this.imageInfo)
				.build();
	}
}
