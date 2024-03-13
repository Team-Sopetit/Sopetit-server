package com.soptie.server.routine.entity.daily;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
public class DailyTheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theme_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	@Embedded
	private RoutineImage imageInfo;

	@Builder
	public DailyTheme(String name, RoutineImage imageInfo) {
		this.name = name;
		this.imageInfo = imageInfo;
	}

	public DailyTheme(Long id, String name, String iconImageUrl, String backgroundImageUrl) {
		this.id = id;
		this.name = name;
		this.imageInfo = new RoutineImage(iconImageUrl, backgroundImageUrl);
	}
}
