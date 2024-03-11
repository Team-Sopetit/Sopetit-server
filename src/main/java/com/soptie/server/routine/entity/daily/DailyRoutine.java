package com.soptie.server.routine.entity.daily;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
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

	public DailyRoutine(Long id, String content, DailyTheme theme) {
		this.id = id;
		this.content = content;
		this.theme = theme;
	}
}
