package com.soptie.server.doll.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Doll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doll_id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private DollType dollType;

	@Embedded
	private DollImage imageInfo;

	public Doll(DollType dollType, DollImage imageInfo) {
		this.dollType = dollType;
		this.imageInfo = imageInfo;
	}
}
