package com.soptie.server.persistence.entity;

import com.soptie.server.domain.doll.Doll;
import com.soptie.server.domain.doll.DollImageUrls;
import com.soptie.server.domain.doll.DollType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "doll", schema = "softie")
public class DollEntity extends BaseEntity {
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private DollType type;
	private String faceImageUrl;
	private String attentionImageUrl;
	private String frameImageUrl;

	public Doll toDomain() {
		return Doll.builder()
			.type(this.type)
			.imageUrls(toImageUrls())
			.build();
	}

	private DollImageUrls toImageUrls() {
		return DollImageUrls.builder()
			.faceImageUrl(this.faceImageUrl)
			.attentionImageUrl(this.attentionImageUrl)
			.frameImageUrl(this.frameImageUrl)
			.build();
	}
}
