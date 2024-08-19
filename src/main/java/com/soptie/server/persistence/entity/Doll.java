package com.soptie.server.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Doll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doll_id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private DollType dollType;

	@Embedded
	private DollImageLinks imageLinks;

	public Doll(DollType dollType, DollImageLinks imageLinks) {
		this.dollType = dollType;
		this.imageLinks = imageLinks;
	}

	public Doll(Long id, DollType dollType, String faceImageUrl) {
		this.id = id;
		this.dollType = dollType;
		this.imageLinks = new DollImageLinks(faceImageUrl, "", "");
	}
}
