package com.soptie.server.memberdoll.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.memberdoll.entity.MemberDoll;
import com.soptie.server.memberdoll.repository.MemberDollRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDollSaver {

	private final MemberDollRepository memberDollRepository;

	public void save(MemberDoll memberDoll) {
		memberDollRepository.save(memberDoll);
	}
}
