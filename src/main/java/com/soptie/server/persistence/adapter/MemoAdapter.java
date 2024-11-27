package com.soptie.server.persistence.adapter;

import java.time.LocalDate;

import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.memo.Memo;
import com.soptie.server.persistence.entity.MemoEntity;
import com.soptie.server.persistence.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemoAdapter {

	private final MemoRepository memoRepository;

	public Memo save(final LocalDate achievedDate, final String content, final Member member) {
		return memoRepository.save(new MemoEntity(achievedDate, content, member)).toDomain();
	}

	public Memo findByIdAndMemberId(final long id, final long memberId) {
		return memoRepository.findByIdAndMemberId(id, memberId)
			.orElseThrow(() -> new SoftieException(
				ExceptionCode.NOT_FOUND,
				"Member ID: " + memberId + " Memo ID: " + id)).toDomain();
	}
}
