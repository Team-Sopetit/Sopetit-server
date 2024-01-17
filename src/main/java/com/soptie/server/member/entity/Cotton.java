package com.soptie.server.member.entity;

import static com.soptie.server.member.message.ErrorCode.*;

import com.soptie.server.member.exception.MemberException;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class Cotton {
	private int dailyCottonCount;
	private int happinessCottonCount;

	protected void addDailyCotton() {
		this.dailyCottonCount++;
	}

	protected void addHappinessCotton(){
		this.happinessCottonCount++;
	}

	protected int subtractAndGetCotton(CottonType type) {
		return switch (type) {
			case DAILY -> subtractAndGetDailyCotton();
			case HAPPINESS -> subtractAndGetHappinessCotton();
		};
	}

	private int subtractAndGetDailyCotton() {
		checkCount(this.dailyCottonCount);
		this.dailyCottonCount--;
		return this.dailyCottonCount;
	}

	private int subtractAndGetHappinessCotton() {
		checkCount(this.happinessCottonCount);
		this.happinessCottonCount--;
		return this.happinessCottonCount;
	}

	private void checkCount(int count) {
		if (count <= 0) {
			throw new MemberException(NOT_ENOUGH_COTTON);
		}
	}
}
