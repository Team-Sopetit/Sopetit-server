package com.soptie.server.expert.entity;

import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Expert {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "expert_id")
	private Long id;

	@NotNull
	private String name;

	private String job;

	private String profileImageUrl;

	@Column(columnDefinition = "TEXT")
	private String description;
}
