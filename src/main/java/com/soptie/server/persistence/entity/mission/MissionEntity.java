package com.soptie.server.persistence.entity.mission;

import com.soptie.server.domain.challenge.Mission;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "mission", schema = "softie")
public class MissionEntity extends BaseEntity {
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private String requiredTime;
	@Column(nullable = false)
	private String place;
	@Column(nullable = false)
	private long challengeId;

	public Mission toDomain() {
		return Mission.builder()
			.id(this.id)
			.content(this.content)
			.description(this.description)
			.requiredTime(this.requiredTime)
			.place(this.place)
			.challengeId(this.challengeId)
			.build();
	}
}
