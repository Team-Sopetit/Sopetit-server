package com.soptie.server.persistence.entity;

import com.soptie.server.domain.theme.Theme;
import com.soptie.server.domain.theme.ThemeImageUrls;

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
	private String iconImageUrl;
	private String backgroundImageUrl;
	private String color;
	private Long makerId;

	public Theme toDomain() {
		return Theme.builder()
			.id(this.id)
			.name(this.name)
			.comment(this.comment)
			.description(this.description)
			.imageUrls(toImageUrls())
			.color(this.color)
			.makerId(this.makerId)
			.build();
	}

	private ThemeImageUrls toImageUrls() {
		return ThemeImageUrls.builder()
			.iconImageUrl(this.iconImageUrl)
			.backgroundImageUrl(this.backgroundImageUrl)
			.build();
	}
}
