package com.soptie.server.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.soptie.server.common.converter.StringListConverter;
import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberRoutine.entity.MemberDailyRoutine;
import com.soptie.server.memberRoutine.entity.MemberHappinessRoutine;
import com.soptie.server.routine.entity.daily.DailyTheme;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Member extends BaseTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long id;

	@Enumerated(value = EnumType.STRING)
	private SocialType socialType;
	private String socialId;

	private String refreshToken;

	private int feedCount;

	@Column(columnDefinition = "TEXT", nullable = false)
	@Convert(converter = StringListConverter.class)
	private List<DailyTheme> themes;

	@OneToOne
	@JoinColumn(name = "doll_id")
	private MemberDoll memberDoll;

	@OneToMany(mappedBy = "member")
	private final List<MemberDailyRoutine> dailyRoutines = new ArrayList<>();

	@OneToOne
	@JoinColumn(name = "happiness_routine_id")
	private MemberHappinessRoutine happinessRoutine;
}
