package com.soptie.server.persistence.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.Member;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDeleter {

	private final MemberRepository memberRepository;

	public void delete(Member member) {
		memberRepository.delete(member);
	}
}
