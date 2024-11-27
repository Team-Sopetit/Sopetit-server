package com.soptie.server.persistence.adapter;

import java.time.LocalDate;

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
}
