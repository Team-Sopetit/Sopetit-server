package com.soptie.server.support.fixture;

import com.soptie.server.theme.entity.ThemeImageInfo;
import com.soptie.server.theme.entity.Theme;
import com.soptie.server.theme.entity.ThemeType;

public class ThemeFixture {

	private Long id;
	private String name = "default";
	private String modifier = "default";
	private String description = "default";
	private String color;
	private ThemeType type = ThemeType.BASIC;
	private final ThemeImageInfo imageInfo = new ThemeImageInfo("https://...", "https://...", "https://...", "https://...", "https://...");

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

	public ThemeFixture modifier(String modifier) {
		this.modifier = modifier;
		return this;
	}

	public ThemeFixture description(String description) {
		this.description = description;
		return this;
	}

	public ThemeFixture color(String color) {
		this.color = color;
		return this;
	}

	public ThemeFixture type(ThemeType type) {
		this.type = type;
		return this;
	}

	public Theme build() {
		return new Theme(id, name, modifier, description, color, type, imageInfo);
	}
}
