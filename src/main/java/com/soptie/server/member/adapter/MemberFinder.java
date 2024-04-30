package com.soptie.server.member.adapter;

import static com.soptie.server.member.message.ErrorCode.*;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;

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
