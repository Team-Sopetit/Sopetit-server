package com.soptie.server.persistence.entity;

import com.soptie.server.domain.theme.Theme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "theme", schema = "softie")
public class ThemeEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String comment;
	@Column(nullable = false)
	private String description;
	@Column(nullable = false)
	private int sequence;
	private Long makerId;

	public Theme toDomain() {
		return Theme.builder()
			.id(this.id)
			.name(this.name)
			.comment(this.comment)
			.description(this.description)
			.sequence(this.sequence)
			.makerId(this.makerId)
			.build();
	}
}
