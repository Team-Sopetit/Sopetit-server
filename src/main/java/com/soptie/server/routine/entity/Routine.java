package com.soptie.server.routine.entity;

import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.FetchType.*;
import static jakarta.persistence.GenerationType.*;

import com.soptie.server.theme.entity.Theme;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
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
public class Routine {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "routine_id")
	private Long id;

	@NotNull
	private String content;

	@Enumerated(value = STRING)
	private RoutineType type;

	@ManyToOne(fetch = LAZY)
	@JoinColumn(name = "theme_id")
	private Theme theme;
}
