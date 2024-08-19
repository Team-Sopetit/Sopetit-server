package com.soptie.server.persistence.adapter;

import static com.soptie.server.common.message.MemberErrorCode.*;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.common.exception.MemberException;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberFinder {

	private final MemberRepository memberRepository;

	public Member findById(long id) {
		return memberRepository.findById(id)
				.orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}
}
