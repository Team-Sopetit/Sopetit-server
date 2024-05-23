package com.soptie.server.memberDoll.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.repository.MemberDollRepository;
import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemberDollSaver {

    private final MemberDollRepository memberDollRepository;

    public void save(MemberDoll memberDoll) {
        memberDollRepository.save(memberDoll);
    }
}
