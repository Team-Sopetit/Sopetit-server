package com.soptie.server.member.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDeleter {

	private final MemberRepository memberRepository;

	public void delete(Member member) {
		memberRepository.delete(member);
	}
}
