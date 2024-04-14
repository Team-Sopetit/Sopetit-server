package com.soptie.server.routine.entity.challenge;

import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import com.soptie.server.routine.entity.Routine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Challenge {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "routine_id")
	private Long id;

	@NotNull
	private String content;

	private String description;

	private String requiredTime;

	private String place;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "routine_id")
	private Routine routine;
}
