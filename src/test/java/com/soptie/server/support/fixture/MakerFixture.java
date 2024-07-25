package com.soptie.server.support.fixture;

import java.util.List;

import com.soptie.server.maker.entity.Maker;

public class MakerFixture {

	private Long id;
	private String name;
	private String job;
	private String profileImageUrl;
	private String description;
	private String content;
	private Long themeId;
	private List<String> tags;

	private MakerFixture() {
	}

	public static MakerFixture maker() {
		return new MakerFixture();
	}

	public MakerFixture id(Long id) {
		this.id = id;
		return this;
	}

	public MakerFixture name(String name) {
		this.name = name;
		return this;
	}

	public MakerFixture job(String job) {
		this.job = job;
		return this;
	}

	public MakerFixture profileImageUrl(String profileImageUrl) {
		this.profileImageUrl = profileImageUrl;
		return this;
	}

	public MakerFixture description(String description) {
		this.description = description;
		return this;
	}

	public MakerFixture content(String content) {
		this.content = content;
		return this;
	}

	public MakerFixture themeId(Long themeId) {
		this.themeId = themeId;
		return this;
	}

	public MakerFixture tags(List<String> tags) {
		this.tags = tags;
		return this;
	}

	public Maker build() {
		return new Maker(id, name, job, profileImageUrl, description, content, themeId, tags);
	}
}
