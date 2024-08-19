package com.soptie.server.persistence.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.entity.MemberDoll;
import com.soptie.server.persistence.repository.MemberDollRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDollSaver {

	private final MemberDollRepository memberDollRepository;

	public void save(MemberDoll memberDoll) {
		memberDollRepository.save(memberDoll);
	}
}
