package com.soptie.server.theme.entity;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Theme {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "theme_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String modifier;

	@Embedded
	private ThemeImageInfo imageInfo;

	private String color;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String description;

	@Enumerated(value = STRING)
	@Column(nullable = false)
	private ThemeType type;

	public Theme(
			Long id,
			String name,
			String modifier,
			String description,
			String color,
			ThemeType type,
			ThemeImageInfo imageInfo
	) {
		this.id = id;
		this.name = name;
		this.modifier = modifier;
		this.description = description;
		this.color = color;
		this.type = type;
		this.imageInfo = imageInfo;
	}
}
