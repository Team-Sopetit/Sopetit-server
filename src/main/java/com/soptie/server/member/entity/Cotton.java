package com.soptie.server.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Cotton {
	private int dailyCottonCount;
	private int happinessCottonCount;

	protected void addDailyCotton() {
		this.dailyCottonCount++;
	}

	protected int subDailyCotton() {
		this.dailyCottonCount--;
		return this.dailyCottonCount;
	}

	protected int subHappinessCotton() {
		this.happinessCottonCount--;
		return this.happinessCottonCount;
	}
}
