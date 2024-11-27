package com.soptie.server.domain.memo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.memo.CreateMemoRequest;
import com.soptie.server.api.controller.dto.response.memo.CreateMemoResponse;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemoAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemoService {

	private final MemberAdapter memberAdapter;
	private final MemoAdapter memoAdapter;

	@Transactional
	public CreateMemoResponse create(final long memberId, final CreateMemoRequest request) {
		val member = memberAdapter.findById(memberId);
		val memo = memoAdapter.save(request.achievedDate(), request.content(), member);
		return CreateMemoResponse.from(memo);
	}
}
