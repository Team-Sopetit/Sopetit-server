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
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

	//TODO: 2번째 생성자 활용(Entity 보호)
	public MemberEntity(SocialType socialType, String socialId) {
		this.socialType = socialType;
		this.socialId = socialId;
		this.basicCottonCount = 0;
		this.rainbowCottonCount = 0;
	}

	public MemberEntity(Member member) {
		this.socialType = member.getSocialInfo().getSocialType();
		this.socialId = member.getSocialInfo().getSocialId();
		this.refreshToken = member.getRefreshToken();
		this.basicCottonCount = member.getCottonInfo().getBasicCottonCount();
		this.rainbowCottonCount = member.getCottonInfo().getRainbowCottonCount();
	}

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
