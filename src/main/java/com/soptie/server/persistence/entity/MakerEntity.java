package com.soptie.server.persistence.entity;

import com.soptie.server.domain.maker.Maker;
import com.soptie.server.domain.maker.Tags;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "maker", schema = "softie")
public class MakerEntity extends BaseEntity {
	@Column(nullable = false)
	private String name;
	private String introductionUrl;
	private String profileImageUrl;
	private String tags;

	public MakerEntity(Maker maker) {
		this.name = maker.getName();
		this.introductionUrl = maker.getIntroductionUrl();
		this.profileImageUrl = maker.getProfileImageUrl();
		this.tags = maker.getTags().getContents();
	}

	public Maker toDomain() {
		return Maker.builder()
			.id(this.id)
			.name(this.name)
			.introductionUrl(this.introductionUrl)
			.profileImageUrl(this.profileImageUrl)
			.tags(new Tags(tags))
			.build();
	}
}
