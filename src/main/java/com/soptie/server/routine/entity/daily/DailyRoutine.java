package com.soptie.server.routine.entity.daily;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DailyRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "routine_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_id")
	private DailyTheme theme;

	@Builder
	public DailyRoutine(String content, DailyTheme theme) {
		this.content = content;
		this.theme = theme;
	}
}
