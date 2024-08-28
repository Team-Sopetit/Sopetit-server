package com.soptie.server.persistence.entity;

import com.soptie.server.domain.challenge.Challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge", schema = "softie")
public class ChallengeEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private long themeId;

	public Challenge toDomain() {
		return Challenge.builder()
			.id(this.id)
			.name(this.name)
			.themeId(this.themeId)
			.build();
	}
}
