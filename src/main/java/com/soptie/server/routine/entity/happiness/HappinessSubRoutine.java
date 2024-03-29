package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class HappinessSubRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_routine_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(columnDefinition = "TEXT")
	private String detailContent;

	private String timeTaken;

	private String place;

	@ManyToOne
	@JoinColumn(name = "routine_id")
	private HappinessRoutine routine;

	@Builder
	public HappinessSubRoutine(String content, String detailContent, String timeTaken, String place, HappinessRoutine routine) {
		this.content = content;
		this.detailContent = detailContent;
		this.timeTaken = timeTaken;
		this.place = place;
		this.routine = routine;
	}

	public HappinessSubRoutine(Long id, String content, String detailContent, String timeTaken, String place, HappinessRoutine routine) {
		this.id = id;
		this.content = content;
		this.detailContent = detailContent;
		this.timeTaken = timeTaken;
		this.place = place;
		this.routine = routine;
	}
}
