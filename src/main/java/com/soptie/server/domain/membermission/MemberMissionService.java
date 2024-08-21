package com.soptie.server.domain.membermission;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.membermission.CreateMemberMissionRequest;
import com.soptie.server.api.controller.dto.response.membermission.CreateMemberMissionResponse;
import com.soptie.server.api.controller.dto.response.membermission.GetMemberMissionResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.persistence.adapter.ChallengeAdapter;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberMissionAdapter;
import com.soptie.server.persistence.adapter.MissionAdapter;
import com.soptie.server.persistence.adapter.ThemeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionService {
	private final MemberMissionAdapter memberMissionAdapter;
	private final ThemeAdapter themeAdapter;
	private final ChallengeAdapter challengeAdapter;
	private final MissionAdapter missionAdapter;
	private final MemberAdapter memberAdapter;

	@Transactional
	public CreateMemberMissionResponse createMemberMission(long memberId, CreateMemberMissionRequest request) {
		if (memberMissionAdapter.findByMember(memberId).isPresent()) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "이미 미션을 추가한 회원");
		}
		val member = memberAdapter.findById(memberId);
		val mission = missionAdapter.findById(request.subRoutineId());
		val savedMemberMission = memberMissionAdapter.save(member, mission);
		return CreateMemberMissionResponse.of(savedMemberMission);
	}

	@Transactional
	public void deleteMemberMission(long memberId, long missionId) {
		val memberMission = memberMissionAdapter.findByMember(memberId)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Member ID: " + memberId));
		if (memberMission.getId() != missionId) {
			throw new SoftieException(
				ExceptionCode.NOT_AVAILABLE,
				"Member ID: " + memberId + ", Mission ID: " + missionId);
		}
		memberMissionAdapter.delete(memberMission);
	}

	@Transactional
	public void achieveMemberMission(long memberId, long missionId) {
		val memberMission = memberMissionAdapter.findByMember(memberId)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Member ID: " + memberId));
		if (memberMission.getId() != missionId) {
			throw new SoftieException(
				ExceptionCode.NOT_AVAILABLE,
				"Member ID: " + memberId + ", Mission ID: " + missionId);
		}
		memberMission.achieve();
		memberMissionAdapter.update(memberMission);
		memberMissionAdapter.delete(memberMission);
	}

	public Optional<GetMemberMissionResponse> getMemberMission(long memberId) {
		return memberMissionAdapter.findByMember(memberId).map(this::toGetMemberMissionResponse);
	}

	private GetMemberMissionResponse toGetMemberMissionResponse(MemberMission memberMission) {
		val mission = missionAdapter.findById(memberMission.getMissionId());
		val challenge = challengeAdapter.findById(mission.getChallengeId());
		val theme = themeAdapter.findById(challenge.getThemeId());
		return GetMemberMissionResponse.of(theme, challenge, mission);
	}
}
