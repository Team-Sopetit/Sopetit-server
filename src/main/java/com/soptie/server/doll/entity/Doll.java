package com.soptie.server.doll.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Doll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doll_id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private DollType dollType;

	@Embedded
	private DollImage imageInfo;
}
