package com.soptie.server.persistence.entity;

import com.soptie.server.domain.routine.Routine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "routine", schema = "softie")
public class RoutineEntity extends BaseEntity {
	@Column(nullable = false)
	private String content;
	@Column(nullable = false)
	private long themeId;

	public Routine toDomain() {
		return Routine.builder()
			.id(this.id)
			.content(this.content)
			.themeId(this.themeId)
			.build();
	}
}
