package com.soptie.server.theme.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import com.soptie.server.expert.entity.Expert;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

	private String name;

	@Embedded
	private ThemeImageInfo imageInfo;

	private String color;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "expert_id")
	private Expert expert;

	public Theme(Long id, String name, String color, ThemeImageInfo imageInfo) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.imageInfo = imageInfo;
	}
}
