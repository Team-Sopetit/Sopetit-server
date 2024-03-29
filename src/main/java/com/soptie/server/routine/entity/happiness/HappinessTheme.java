package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
public class HappinessTheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theme_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String nameColor;

	@Embedded
	private RoutineImage imageInfo;

	@Builder
	public HappinessTheme(String name, String nameColor, RoutineImage imageInfo) {
		this.name = name;
		this.nameColor = nameColor;
		this.imageInfo = imageInfo;
	}

	public HappinessTheme(Long id, String name, String nameColor, String iconImageUrl, String contentImageUrl, String twinkleIconImageUrl) {
		this.id = id;
		this.name = name;
		this.nameColor = nameColor;
		this.imageInfo = new RoutineImage(iconImageUrl, contentImageUrl, twinkleIconImageUrl);
	}
}
