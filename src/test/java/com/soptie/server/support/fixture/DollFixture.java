package com.soptie.server.support.fixture;

import com.soptie.server.domain.doll.DollType;

public class DollFixture {

	private Long id;
	private DollType dollType;
	private String faceImageUrl;

	private DollFixture() {
	}

	public static DollFixture doll() {
		return new DollFixture();
	}

	public DollFixture id(Long id) {
		this.id = id;
		return this;
	}

	public DollFixture dollType(DollType dollType) {
		this.dollType = dollType;
		return this;
	}

	public DollFixture faceImageUrl(String faceImageUrl) {
		this.faceImageUrl = faceImageUrl;
		return this;
	}

	public Doll build() {
		return new Doll(id, dollType, faceImageUrl);
	}
}
