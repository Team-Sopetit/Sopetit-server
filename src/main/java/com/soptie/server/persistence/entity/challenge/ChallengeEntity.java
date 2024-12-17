package com.soptie.server.persistence.entity.challenge;

import com.soptie.server.domain.challenge.Challenge;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "challenge", schema = "softie")
public class ChallengeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
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
