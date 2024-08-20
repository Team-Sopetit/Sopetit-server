package com.soptie.server.persistence.entity;

import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.MemberCotton;
import com.soptie.server.domain.member.Social;
import com.soptie.server.domain.member.SocialType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name = "member", schema = "softie")
public class MemberEntity extends BaseEntity {
	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false)
	private SocialType socialType;
	@Column(nullable = false)
	private String socialId;
	private String refreshToken;
	@Column(nullable = false)
	private int basicCottonCount;
	@Column(nullable = false)
	private int rainbowCottonCount;

	public void update(Member member) {
		this.refreshToken = member.getRefreshToken();
		this.basicCottonCount = member.getCottonInfo().getBasicCottonCount();
		this.rainbowCottonCount = member.getCottonInfo().getRainbowCottonCount();
	}

	public Member toDomain() {
		return Member.builder()
			.id(this.id)
			.socialInfo(toSocialInfo())
			.refreshToken(this.refreshToken)
			.cottonInfo(toCottonInfo())
			.build();
	}

	private Social toSocialInfo() {
		return Social.builder()
			.socialType(this.socialType)
			.socialId(this.socialId)
			.build();
	}

	private MemberCotton toCottonInfo() {
		return MemberCotton.builder()
			.basicCottonCount(this.basicCottonCount)
			.rainbowCottonCount(this.rainbowCottonCount)
			.build();
	}
}
