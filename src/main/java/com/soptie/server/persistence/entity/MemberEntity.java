package com.soptie.server.persistence.entity;

import java.time.LocalDateTime;

import com.soptie.server.common.constants.DomainConstants;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = DomainConstants.MEMBER, schema = DomainConstants.SOFTIE)
public class MemberEntity extends BaseEntity {

	@Column(nullable = false)
	@Enumerated(value = EnumType.STRING)
	private SocialType socialType;

	@Column(nullable = false)
	private String socialId;

	@Column
	private String refreshToken;

	@Column(nullable = false)
	private int basicCottonCount;

	@Column(nullable = false)
	private int rainbowCottonCount;

	@Column
	private LocalDateTime lastVisitDateTime;

	public MemberEntity(Member member) {
		this.socialType = member.getSocialInfo().getSocialType();
		this.socialId = member.getSocialInfo().getSocialId();
		this.refreshToken = member.getRefreshToken();
		this.basicCottonCount = member.getCottonInfo().getBasicCottonCount();
		this.rainbowCottonCount = member.getCottonInfo().getRainbowCottonCount();
		this.lastVisitDateTime = member.getLastVisitDateTime();
	}

	public void update(Member member) {
		this.refreshToken = member.getRefreshToken();
		this.basicCottonCount = member.getCottonInfo().getBasicCottonCount();
		this.rainbowCottonCount = member.getCottonInfo().getRainbowCottonCount();
		this.lastVisitDateTime = member.getLastVisitDateTime();
	}
}
