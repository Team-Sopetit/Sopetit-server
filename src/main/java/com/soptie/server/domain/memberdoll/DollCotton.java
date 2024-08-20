package com.soptie.server.domain.memberdoll;

import com.soptie.server.domain.member.CottonType;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class DollCotton {
	private int basicCottonCount;
	private int rainbowCottonCount;

	public void giveCotton(CottonType type) {
		switch (type) {
			case DAILY -> basicCottonCount++;
			case HAPPINESS -> rainbowCottonCount++;
		}
	}
}
