package com.soptie.server.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class MemberCotton {
	protected int basicCottonCount;
	protected int rainbowCottonCount;

	public void addBasicCottonCount() {
		this.basicCottonCount++;
	}

	public void addRainbowCottonCount() {
		this.rainbowCottonCount++;
	}

	public void subtractCotton(CottonType type) {
		switch (type) {
			case DAILY -> basicCottonCount--;
			case HAPPINESS -> rainbowCottonCount--;
		}
	}

	public int getCottonCount(CottonType type) {
		return switch (type) {
			case DAILY -> basicCottonCount;
			case HAPPINESS -> rainbowCottonCount;
		};
	}
}
