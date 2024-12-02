package com.soptie.server.persistence.adapter;

import java.time.LocalDate;
import java.util.List;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memo.Memo;
import com.soptie.server.persistence.entity.MemoEntity;
import com.soptie.server.persistence.repository.MemoRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemoAdapter {

	private final MemoRepository memoRepository;

	public Memo save(final LocalDate achievedDate, final String content, final Member member) {
		return memoRepository.save(new MemoEntity(achievedDate, content, member)).toDomain();
	}

	public void update(final Memo memo) {
		val memoEntity = find(memo.getId());
		memoEntity.update(memo);
	}

	public void delete(final Memo memo) {
		memoRepository.deleteById(memo.getId());
	}

	public Memo findByIdAndMemberId(final long memoId, final long memberId) {
		return memoRepository.findByIdAndMemberId(memoId, memberId)
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Member ID: " + memberId + " Memo ID: " + memoId)).toDomain();
	}

	public Memo findById(final long memoId) {
		return memoRepository.findById(memoId)
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				" Memo ID: " + memoId)).toDomain();
	}

	public List<Memo> findAllByMemberIdAndAchievedDateBetween(
		final long memberId,
		final LocalDate startDate,
		final LocalDate endDate
	) {
		return memoRepository.findAllByMemberIdAndAchievedDateBetween(memberId, startDate, endDate).stream()
			.map(MemoEntity::toDomain).toList();
	}

	private MemoEntity find(final long id) {
		return memoRepository.findById(id)
			.orElseThrow(() -> new SoftieException(ExceptionCode.NOT_FOUND, "Memo ID: " + id));
	}
}
