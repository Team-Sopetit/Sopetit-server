package com.soptie.server.routine.entity.happiness;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class HappinessSubRoutine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_routine_id")
	private Long id;

	@Column(nullable = false)
	private String content;

	@Column(columnDefinition = "TEXT")
	private String detailContent;

	@ManyToOne
	@JoinColumn(name = "routine_id")
	private HappinessRoutine routine;
}
