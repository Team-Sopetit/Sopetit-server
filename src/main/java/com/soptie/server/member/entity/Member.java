package com.soptie.server.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class Member extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private SocialType socialType;
	private String socialId;

	private String refreshToken;

	@Embedded
	private Cotton cottonInfo;

	@OneToOne
	@JoinColumn(name = "doll_id")
	private MemberDoll memberDoll;

	@OneToMany(mappedBy = "member")
	private final List<MemberDailyRoutine> dailyRoutines = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "happiness_routine_id")
	private MemberHappinessRoutine happinessRoutine;

	@Builder
	public Member(SocialType socialType, String socialId) {
		this.socialType = socialType;
		this.socialId = socialId;
		this.cottonInfo = new Cotton(0, 0);
	}

	public void initHappinessRoutine() {
		this.happinessRoutine = null;
	}

	public void addHappinessRoutine(MemberHappinessRoutine happinessRoutine) {
		this.happinessRoutine = happinessRoutine;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void addDailyCotton() {
		this.cottonInfo.addDailyCottonCotton();
	}
}
