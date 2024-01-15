package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
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
}
