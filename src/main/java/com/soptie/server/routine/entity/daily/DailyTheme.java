package com.soptie.server.routine.entity.daily;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class DailyTheme {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "theme_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	private String imageUrl;
}
