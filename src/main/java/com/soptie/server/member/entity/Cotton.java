package com.soptie.server.member.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Cotton {
	private int dailyCottonCount;
	private int happinessCottonCount;

	protected void addDailyCottonCotton() {
		this.dailyCottonCount++;
	}
}
