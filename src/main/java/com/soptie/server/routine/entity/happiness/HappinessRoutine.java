package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class HappinessRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "routine_id")
	private Long id;

	@Column(nullable = false)
	private String title;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_id")
	private HappinessTheme theme;

	@Builder
	public HappinessRoutine(String title, HappinessTheme theme) {
		this.title = title;
		this.theme = theme;
	}

	public HappinessRoutine(Long id, String title, HappinessTheme theme) {
		this.id = id;
		this.title = title;
		this.theme = theme;
	}

}
