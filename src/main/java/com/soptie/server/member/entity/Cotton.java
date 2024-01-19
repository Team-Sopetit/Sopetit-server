package com.soptie.server.member.entity;

import static com.soptie.server.common.util.Constant.MIN_COTTON_COUNT;
import static com.soptie.server.member.message.ErrorCode.*;

import com.soptie.server.member.exception.MemberException;
import com.soptie.server.memberDoll.entity.MemberDoll;

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

	protected int subtractAndGetCotton(CottonType type, MemberDoll memberDoll) {
		return switch (type) {
			case DAILY -> subtractAndGetDailyCotton();
			case HAPPINESS -> subtractAndGetHappinessCotton(memberDoll);
		};
	}

	private int subtractAndGetDailyCotton() {
		checkCount(this.dailyCottonCount);
		this.dailyCottonCount--;
		return this.dailyCottonCount;
	}

	private int subtractAndGetHappinessCotton(MemberDoll memberDoll) {
		checkCount(this.happinessCottonCount);
		this.happinessCottonCount--;
		memberDoll.addHappinessCottonCount();
		return this.happinessCottonCount;
	}

	private void checkCount(int count) {
		if (count <= MIN_COTTON_COUNT) {
			throw new MemberException(NOT_ENOUGH_COTTON);
		}
	}
}
