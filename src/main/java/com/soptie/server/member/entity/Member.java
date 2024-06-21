package com.soptie.server.member.entity;

import static com.soptie.server.member.message.ErrorCode.*;
import static jakarta.persistence.EnumType.*;
import static jakarta.persistence.GenerationType.*;

import java.util.Objects;

import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.routine.entity.RoutineType;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseTime {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Enumerated(value = STRING)
	private SocialType socialType;
	private String socialId;

	private String refreshToken;

	@Embedded
	private Cotton cottonInfo;

	@OneToOne
	@JoinColumn(name = "doll_id")
	private MemberDoll memberDoll;

	@Builder
	public Member(SocialType socialType, String socialId) {
		this.socialType = socialType;
		this.socialId = socialId;
		this.cottonInfo = new Cotton(0, 0);
	}

	public Member(Long id, MemberDoll memberDoll, int dailyCottonCount) {
		this.id = id;
		this.cottonInfo = new Cotton(dailyCottonCount, 0);
		this.memberDoll = memberDoll;
	}

	public Member(Long id) {
		this.id = id;
	}

	public void registerMemberDoll(MemberDoll memberDoll) {
		this.memberDoll = memberDoll;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void resetRefreshToken() {
		this.refreshToken = null;
	}

	public void addCottonCount(RoutineType type) {
		switch (type) {
			case DAILY -> this.cottonInfo.addDailyCotton();
			case CHALLENGE -> this.cottonInfo.addHappinessCotton();
		}
	}

	public int subtractAndGetCotton(CottonType type) {
		if (Objects.isNull(this.memberDoll)) {
			throw new MemberException(NOT_EXIST_DOLL);
		}
		return cottonInfo.subtractAndGetCotton(type, this.memberDoll);
	}

	public void checkMemberDollExist() {
		if (!isMemberDollExist()) {
			throw new MemberException(NOT_EXIST_DOLL);
		}
	}

	public void checkMemberDollNonExist() {
		if (isMemberDollExist()) {
			throw new MemberException(EXIST_PROFILE);
		}
	}

	public boolean isMemberDollExist() {
		return Objects.nonNull(this.getMemberDoll());
	}
}
