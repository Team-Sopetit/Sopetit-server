package com.soptie.server.persistence.entity;

import com.soptie.server.domain.doll.Doll;
import com.soptie.server.domain.doll.DollType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "doll", schema = "softie")
public class DollEntity extends BaseEntity {
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private DollType type;

	public Doll toDomain() {
		return Doll.builder()
			.id(this.id)
			.type(this.type)
			.build();
	}
}
