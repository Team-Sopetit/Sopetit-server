package com.soptie.server.member.entity;

import static com.soptie.server.common.config.ValueConfig.*;
import static com.soptie.server.doll.message.ErrorCode.*;
import static com.soptie.server.member.message.ErrorCode.*;
import static com.soptie.server.routine.message.ErrorCode.*;

import com.soptie.server.common.entity.BaseTime;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.exception.MemberDollException;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.routine.entity.daily.DailyRoutine;
import com.soptie.server.routine.exception.RoutineException;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

	public Member(Long id, MemberDoll memberDoll, int dailyCottonCount) {
		this.id = id;
		this.cottonInfo = new Cotton(dailyCottonCount, 0);
		this.memberDoll = memberDoll;
	}

	public Member(Long id) {
		this.id = id;
	}

	public void setMemberDoll(MemberDoll memberDoll) {
		this.memberDoll = memberDoll;
	}

	public void resetHappinessRoutine() {
		this.happinessRoutine = null;
	}

	public void addHappinessRoutine(MemberHappinessRoutine happinessRoutine) {
		this.happinessRoutine = happinessRoutine;
	}

	public void updateRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public void resetRefreshToken() {
		this.refreshToken = null;
	}

	public void addDailyCotton() {
		this.cottonInfo.addDailyCotton();
	}

	public void addHappinessCotton() {
		this.cottonInfo.addHappinessCotton();
	}

	public int subtractAndGetCotton(CottonType type) {
		if (Objects.isNull(this.memberDoll)) {
			throw new MemberDollException(NOT_EXIST_MEMBER_DOLL);
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

	public void checkDailyRoutineAddition() {
		if (this.getDailyRoutines().size() >= DAILY_ROUTINE_MAX_COUNT) {
			throw new RoutineException(CANNOT_ADD_MEMBER_ROUTINE);
		}
	}

	public void checkHappinessRoutineAddition() {
		if (Objects.nonNull(this.getHappinessRoutine())) {
			throw new RoutineException(CANNOT_ADD_MEMBER_ROUTINE);
		}
	}

	public void checkDailyRoutineForMember(MemberDailyRoutine routine) {
		if (!isDailyRoutineForMember(routine)) {
			throw new MemberException(INACCESSIBLE_ROUTINE);
		}
	}

	public boolean isDailyRoutineForMember(MemberDailyRoutine routine) {
		return this.getDailyRoutines().contains(routine);
	}

	public void checkHappinessRoutineForMember(MemberHappinessRoutine routine) {
		if (!this.getHappinessRoutine().equals(routine)) {
			throw new MemberException(INACCESSIBLE_ROUTINE);
		}
	}

	public boolean isNotExistRoutine(DailyRoutine routine) {
		return this.getDailyRoutines().stream()
				.noneMatch(memberRoutine -> memberRoutine.getRoutine().equals(routine));
	}
}
