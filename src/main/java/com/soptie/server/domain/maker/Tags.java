package com.soptie.server.domain.maker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class Tags {
	private final String contents;

	private static final String TAG_SEPARATE = ",";

	public Tags(String tags) {
		this.contents = tags;
	}

	public Tags(List<String> tags) {
		this.contents = String.join(TAG_SEPARATE, tags);
	}

	public List<String> toTagList() {
		return hasContents() ? Arrays.stream(contents.split(TAG_SEPARATE)).toList() : new ArrayList<>();
	}

	private boolean hasContents() {
		return contents != null && !contents.isEmpty();
	}
}
