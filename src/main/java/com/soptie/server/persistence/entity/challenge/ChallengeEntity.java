package com.soptie.server.persistence.entity.challenge;

import com.soptie.server.domain.challenge.Challenge;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge", schema = "softie")
public class ChallengeEntity extends BaseEntity {
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String requiredTime;
	@Column(nullable = false)
	private String place;
	@Column(nullable = false)
	private long themeId;

	public Challenge toDomain() {
		return Challenge.builder()
			.id(this.id)
			.content(this.content)
			.description(this.description)
			.requiredTime(this.requiredTime)
			.place(this.place)
			.themeId(this.themeId)
			.build();
	}
}
