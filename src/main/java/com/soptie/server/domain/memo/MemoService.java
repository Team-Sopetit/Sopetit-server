package com.soptie.server.domain.memo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.memo.CreateMemoRequest;
import com.soptie.server.api.controller.dto.request.memo.ModifyMemoRequest;
import com.soptie.server.api.controller.dto.response.memo.CreateMemoResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemoAdapter;
import com.soptie.server.persistence.adapter.challenge.ChallengeHistoryAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineHistoryAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

	private final MemberAdapter memberAdapter;
	private final MemoAdapter memoAdapter;
	private final ChallengeHistoryAdapter challengeHistoryAdapter;
	private final RoutineHistoryAdapter routineHistoryAdapter;

	@Transactional
	public CreateMemoResponse create(final long memberId, final CreateMemoRequest request) {
		val member = memberAdapter.findById(memberId);
		validateAchievedDate(memberId, request);
		val memo = memoAdapter.save(request.achievedDate(), request.content(), member);
		return CreateMemoResponse.from(memo);
	}

	@Transactional
	public void modify(final long memberId, final long memoId, final ModifyMemoRequest request) {
		val memo = memoAdapter.findByIdAndMemberId(memoId, memberId);
		memo.updateContent(request.content());
		memoAdapter.update(memo);
	}

	@Transactional
	public void delete(final long memberId, final long memoId) {
		val memo = memoAdapter.findById(memoId);
		memo.validateMember(memberId);
		memoAdapter.delete(memo);
	}

	private void validateAchievedDate(final long memberId, final CreateMemoRequest request) {
		if (hasNoRoutineHistory(memberId, request) && hasNoMissionHistory(memberId, request)) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "해당 날짜에 루틴 및 미션 달성 내역이 존재하지 않습니다.");
		}
		if (memoAdapter.isExistByMemberIdAndAchievedDate(memberId, request.achievedDate())) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "해당 날짜에 이미 메모가 존재합니다.");
		}
	}

	private boolean hasNoRoutineHistory(final long memberId, final CreateMemoRequest request) {
		return !routineHistoryAdapter.isExistByMemberIdAndCreatedAt(memberId, request.achievedDate());
	}

	private boolean hasNoMissionHistory(final long memberId, final CreateMemoRequest request) {
		return !challengeHistoryAdapter.isExistByMemberIdAndCreatedAt(memberId, request.achievedDate());
	}
}
