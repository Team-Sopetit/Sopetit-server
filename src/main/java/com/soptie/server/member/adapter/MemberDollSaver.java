package com.soptie.server.member.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.member.entity.MemberDoll;
import com.soptie.server.member.repository.MemberDollRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDollSaver {

	private final MemberDollRepository memberDollRepository;

	public void save(MemberDoll memberDoll) {
		memberDollRepository.save(memberDoll);
	}
}
