package com.soptie.server.routine.entity;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
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
public class Challenge {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "challenge_id")
	private Long id;

	private String content;

	private String description;

	private String requiredTime;

	private String place;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "routine_id")
	private Routine routine;

	public Challenge(Long id, String content, String description, String requiredTime, String place, Routine routine) {
		this.id = id;
		this.content = content;
		this.description = description;
		this.requiredTime = requiredTime;
		this.place = place;
		this.routine = routine;
	}
}
