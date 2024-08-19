package com.soptie.server.persistence.entity;

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
}
