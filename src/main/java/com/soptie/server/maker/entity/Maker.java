package com.soptie.server.maker.entity;

import static jakarta.persistence.GenerationType.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Maker {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "maker_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	private String job;

	private String profileImageUrl;

	@Column(columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private long themeId;
}
